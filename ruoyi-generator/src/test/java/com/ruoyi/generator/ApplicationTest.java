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
                "mall_pms_brand",
                "mall_pms_product_category" ,
                "mall_pms_product",
                "mall_pms_sku"
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
