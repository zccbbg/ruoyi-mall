package com.cyl.h5.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.ums.domain.entity.Address;
import com.cyl.manager.ums.domain.entity.Feedback;
import com.cyl.manager.ums.mapper.AddressMapper;
import com.cyl.manager.ums.domain.dto.AddressDTO;
import com.cyl.manager.ums.service.FeedbackService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.OssUtils;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class H5CommonController {

  @Autowired
  private OssUtils ossUtils;
  @Autowired
  private AddressMapper addressMapper;
  @Autowired
  private RedisService redisService;
  @Autowired
  private FeedbackService feedbackService;
  @Autowired
  private ISysConfigService sysConfigService;

  @ApiOperation("新增意见反馈")
  @Log(title = "意见反馈", businessType = BusinessType.INSERT)
  @PostMapping("/h5/feedback/create")
  public ResponseEntity<Integer> add(@RequestBody Feedback feedback) {
    return ResponseEntity.ok(feedbackService.insert(feedback));
  }


  @GetMapping("/h5/area")
  public AjaxResult getAddressList() {
    String addresses = redisService.getAddressList();
    if (StringUtils.isNotEmpty(addresses)) {
      List<AddressDTO> addressDTOList = JSON.parseArray(addresses, AddressDTO.class);
      if(addressDTOList.size()>0){
        return AjaxResult.success(addressDTOList);
      }
    }
    QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
    addressQueryWrapper.in("level", Arrays.asList(0,1,2));
    List<Address> addressList = addressMapper.selectList(addressQueryWrapper);
    Map<Long, List<Address>> cityMap = addressList.stream().filter(it -> it.getLevel() == 1).collect(Collectors.groupingBy(it -> it.getParentCode()));
    Map<Long, List<Address>> districtMap = addressList.stream().filter(it -> it.getLevel() == 2).collect(Collectors.groupingBy(it -> it.getParentCode()));
    List<AddressDTO> result = new ArrayList<>();
    addressList.stream().filter(it -> it.getLevel() == 0).forEach(it -> {
      AddressDTO dto = new AddressDTO();
      dto.setId(it.getCode());
      dto.setLevel("province");
      dto.setName(it.getName());
      dto.setPid(0L);
      //获取城市列表
      List<AddressDTO> child = new ArrayList<>();
      if (cityMap.containsKey(it.getCode())) {
        cityMap.get(it.getCode()).forEach(city -> {
          AddressDTO cityDto = new AddressDTO();
          cityDto.setId(city.getCode());
          cityDto.setLevel("city");
          cityDto.setName(city.getName());
          cityDto.setPid(city.getParentCode());
          cityDto.setChildren(districtMap.containsKey(city.getCode()) ?
            districtMap.get(city.getCode()).stream().map(district -> {
              AddressDTO districtDto = new AddressDTO();
              districtDto.setId(district.getCode());
              districtDto.setLevel("district");
              districtDto.setName(district.getName());
              districtDto.setPid(district.getParentCode());
              return districtDto;
            }).collect(Collectors.toList()) : Collections.EMPTY_LIST);
          child.add(cityDto);
        });
      }
      dto.setChildren(child);
      result.add(dto);
    });
    redisService.setAddressList(JSON.toJSONString(result));
    return AjaxResult.success(result);
  }

  @PostMapping("/h5/file/upload")
  public AjaxResult uploadFile(MultipartFile file) {
    String url = ossUtils.uploadOneFile(file);
    return AjaxResult.successData(url);
  }

  @GetMapping("/no-auth/config/get")
  public AjaxResult getSysConfig(String configKey) {
    String s = sysConfigService.selectConfigByKey(configKey);
    return AjaxResult.successData(s);
  }

}
