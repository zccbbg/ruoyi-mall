package com.cyl.h5.controller;

import com.cyl.h5.service.H5MemberCartService;
import com.cyl.manager.ums.convert.MemberCartConvert;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.domain.entity.MemberCart;
import com.cyl.manager.ums.domain.query.MemberCartQuery;
import com.cyl.manager.ums.domain.vo.MemberCartVO;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/h5/cart")
public class H5MemberCartController {
    @Autowired
    private H5MemberCartService memberCartService;
    @Autowired
    private MemberCartConvert memberCartConvert;

    /**
     * 当前用户的购物车商品数量
     *
     * @return
     */
    @GetMapping("goodscount")
    public ResponseEntity<Integer> goodscount() {
        return ResponseEntity.ok(memberCartService.mineCartNum());
    }
    /**
     * 当前用户的购物车商品id列表
     *
     * @return
     */
    @GetMapping("cart-ids")
    public ResponseEntity<List<Long>> cartIds() {
        return ResponseEntity.ok(memberCartService.mineCartIds());
    }

    /**
     * 添加购物车
     *
     * @return 购物车商品
     */
    @PostMapping("add")
    public ResponseEntity<Integer> add(@RequestBody MemberCart memberCart) {
        return ResponseEntity.ok(memberCartService.insert(memberCart));
    }

    /**
     * 修改购物车
     *
     * @return 是否修改
     */
    @PostMapping("modify")
    public ResponseEntity<Integer> modify(@Valid @RequestBody MemberCart memberCart) {
        return ResponseEntity.ok(memberCartService.update(memberCart));
    }

    /**
     * 修改购物车
     *
     * @return 是否修改
     */
    @DeleteMapping("remove")
    public ResponseEntity<Integer> remove(@RequestBody String ids) {
        return ResponseEntity.ok(memberCartService.deleteByIds(ids));
    }

    /**
     * 购物车列表
     *
     * @return 购物车列表
     */
    @GetMapping("list")
    public ResponseEntity<List<MemberCartVO>> remove() {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        MemberCartQuery query = new MemberCartQuery();
        query.setMemberId(member.getId());
        return ResponseEntity.ok(memberCartService.selectList(query, null));
    }
}
