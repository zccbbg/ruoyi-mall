package com.cyl.h5.controller;

import com.cyl.manager.ums.domain.entity.MemberAddress;
import com.cyl.manager.ums.domain.query.MemberAddressQuery;
import com.cyl.manager.ums.service.MemberAddressService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("ucenter")
@RestController
public class UserController {
    @Autowired
    private MemberAddressService memberAddressService;

    @GetMapping("user-address")
    public ResponseEntity<List<MemberAddress>> queryPageOfAddress() {
        MemberAddressQuery q = new MemberAddressQuery();
        q.setMemberId(SecurityUtils.getUserId());
        return ResponseEntity.ok(memberAddressService.selectList(q, null));
    }

    @PostMapping("add-update-user-address")
    public ResponseEntity<MemberAddress> addOrUpdateAddress(@RequestBody MemberAddress address) {
        if (address.getId() != null) {
            address.setMemberId(null);
            memberAddressService.updateSelective(address);
        } else {
            address.setMemberId(SecurityUtils.getUserId());
            memberAddressService.insert(address);
        }
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("delete-user-address")
    public ResponseEntity<Integer> deleteUserAddress(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(memberAddressService.deleteUserIds(ids));
    }

    @GetMapping("detail-user-address")
    public ResponseEntity<MemberAddress> detailUserAddress(@RequestParam("id") Long id) {
        return ResponseEntity.ok(memberAddressService.selectByUserAndId(id));
    }
}
