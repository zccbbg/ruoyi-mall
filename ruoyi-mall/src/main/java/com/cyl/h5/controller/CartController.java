package com.cyl.h5.controller;

import com.cyl.pms.pojo.dto.MemberCartDTO;
import com.cyl.ums.convert.MemberCartConvert;
import com.cyl.ums.domain.MemberCart;
import com.cyl.ums.pojo.query.MemberCartQuery;
import com.cyl.ums.pojo.vo.form.AddMemberCartForm;
import com.cyl.ums.pojo.vo.form.UpdateMemberCartForm;
import com.cyl.ums.service.MemberCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/h5/cart")
public class CartController {
    @Autowired
    private MemberCartService memberCartService;
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
    public ResponseEntity<MemberCart> add(@Valid @RequestBody AddMemberCartForm form) {
        return ResponseEntity.ok(memberCartService.insert(form));
    }

    /**
     * 修改购物车
     *
     * @return 是否修改
     */
    @PostMapping("modify")
    public ResponseEntity<Integer> modify(@Valid @RequestBody UpdateMemberCartForm form) {
        return ResponseEntity.ok(memberCartService.update(form));
    }

    /**
     * 修改购物车
     *
     * @return 是否修改
     */
    @DeleteMapping("remove")
    public ResponseEntity<Integer> remove(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(memberCartService.deleteByIds(ids));
    }

    /**
     * 购物车列表
     *
     * @return 购物车列表
     */
    @PostMapping("list")
    public ResponseEntity<Page<MemberCartDTO>> remove(@RequestBody MemberCartQuery query, Pageable pageable) {
        List<MemberCart> list = memberCartService.selectList(query, pageable);
        com.github.pagehelper.Page<?> p = (com.github.pagehelper.Page<?>)list;
        List<MemberCartDTO> resList = memberCartConvert.dos2Dtos(list);
        memberCartService.injectSku(resList);
        return ResponseEntity.ok(new PageImpl<>(resList, pageable, p.getTotal()));
    }
}
