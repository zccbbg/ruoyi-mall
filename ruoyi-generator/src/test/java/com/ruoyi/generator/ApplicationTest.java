package com.ruoyi.generator;

import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.service.IGenTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = SpringAppTest.class)
@ActiveProfiles("local")
public class ApplicationTest {
    @Autowired
    @Qualifier("genTableServiceImpl")
    private IGenTableService genTableService;

    @Test
    public void test2() {
        List<String> tableNames = Arrays.asList(
//                "pms_brand",
//                "pms_product_category" ,
//                "pms_product",
//                "pms_sku"
//                "ums_member",
//                "ums_member_address",
//                "ums_member_wechat",
//                "ums_member_cart"
//                "oms_order",
//                "oms_order_delivery_history",
//                "oms_order_item",
//                "oms_order_operate_history",
//                "oms_aftersale",
//                "oms_aftersale_item"
                "act_coupon_activity",
                "act_member_coupon"
        );
        // 查询表信息
        List<GenTable> tableList = genTableService.selectGenTableByName(tableNames);
        List<GenTable> notExist = new ArrayList<>();
        try {
            tableList.forEach(it -> {
                if (it.getTableId() == null) {
                    it.setAudit(1);
                    notExist.add(it);
                }
            });
            if (notExist.size() > 0) {
                genTableService.importGenTable(notExist, -1L);
            }
            for (String tableName : tableNames) {
                genTableService.generatorCode(tableName);
            }
        } finally {
            // 删除生成
            if (notExist.size() > 0) {
                Long[] ids = notExist.stream().map(GenTable::getTableId).toArray(Long[]::new);
                genTableService.deleteGenTableByIds(ids);
            }
        }
    }

}
