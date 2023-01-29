package com.fjp.lc.test.service;

import com.cyl.ums.service.MemberCartService;
import com.ruoyi.RuoYiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = RuoYiApplication.class)
@ActiveProfiles("dev")
public class ServiceTest {
    @Autowired
    private MemberCartService memberCartService;
    @Test
    public void test1() {
        memberCartService.mineCartNum();
    }
}
