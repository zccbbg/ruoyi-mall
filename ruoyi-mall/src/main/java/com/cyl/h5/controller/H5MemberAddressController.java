package com.cyl.h5.controller;

import com.cyl.h5.service.H5MemberAddressService;
import com.cyl.ums.domain.MemberAddress;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/h5/member/address")
public class H5MemberAddressController {

  @Autowired
  private H5MemberAddressService h5MemberAddressService;

  @GetMapping("/list")
  public AjaxResult getList(){
    return AjaxResult.success(h5MemberAddressService.selectList());
  }

  @GetMapping("/default")
  public ResponseEntity<MemberAddress> getDefault(){
    return ResponseEntity.ok(h5MemberAddressService.getDefault());
  }

  @PostMapping("/create")
  public AjaxResult create(@RequestBody MemberAddress memberAddress){
    return AjaxResult.success(h5MemberAddressService.insert(memberAddress));
  }

  @PutMapping("/update")
  public AjaxResult update(@RequestBody MemberAddress memberAddress){
    return AjaxResult.success(h5MemberAddressService.update(memberAddress));
  }

  @GetMapping("/{id}")
  public AjaxResult getInfo(@PathVariable Long id){
    return AjaxResult.success(h5MemberAddressService.selectById(id));
  }

  @DeleteMapping("/{id}")
  public AjaxResult remove(@PathVariable Long id) {
    return AjaxResult.success(h5MemberAddressService.deleteById(id));
  }
}
