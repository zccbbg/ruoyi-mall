package com.ruoyi.web.controller.common;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.ums.domain.entity.Address;
import com.cyl.manager.ums.mapper.AddressMapper;
import com.cyl.manager.ums.domain.dto.AddressDTO;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file, MimeTypeUtils.isImg(file.getContentType()));
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    @GetMapping("/common/area")
    public AjaxResult getAddressList() {
        String addresses = redisService.getAddressList();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(addresses)) {
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
}
