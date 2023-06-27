package com.cyl.h5.controller;

import com.cyl.h5.service.H5MemberAddressService;
import com.cyl.ums.domain.MemberAddress;
import com.cyl.ums.pojo.dto.MemberAddressDTO;
import com.cyl.ums.pojo.vo.MemberAddressVO;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h5/member/address")
public class H5MemberAddressController {

  @Autowired
  private H5MemberAddressService h5MemberAddressService;

  @GetMapping("/list")
  public ResponseEntity<List<MemberAddressVO>> getList(){
    return ResponseEntity.ok(h5MemberAddressService.selectList());
  }

  @GetMapping("/default")
  public ResponseEntity<MemberAddressVO> getDefault(){
    return ResponseEntity.ok(h5MemberAddressService.getDefault());
  }

  @PostMapping("/create")
  public ResponseEntity<Integer> create(@RequestBody MemberAddressDTO memberAddressDTO){
    return ResponseEntity.ok(h5MemberAddressService.insert(memberAddressDTO));
  }

  @PutMapping("/update")
  public ResponseEntity<Integer> update(@RequestBody MemberAddressDTO memberAddressDTO){
    return ResponseEntity.ok(h5MemberAddressService.update(memberAddressDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberAddressVO> getInfo(@PathVariable Long id){
    return ResponseEntity.ok(h5MemberAddressService.selectById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Integer> remove(@PathVariable Long id) {
    return ResponseEntity.ok(h5MemberAddressService.deleteById(id));
  }
}
