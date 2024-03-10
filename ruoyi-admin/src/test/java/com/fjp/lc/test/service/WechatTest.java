package com.fjp.lc.test.service;

import com.cyl.h5.domain.form.WechatLoginForm;
import com.cyl.manager.ums.service.MemberWechatService;
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
public class WechatTest {
    @Autowired
    private MemberWechatService memberWechatService;
    @Test
    public void testAuth() {
        WechatLoginForm f = new WechatLoginForm();
        f.setCode("081zPgHa1FbRQE0wGIIa1lgb1C1zPgHi");
        memberWechatService.login(f);
    }
}
