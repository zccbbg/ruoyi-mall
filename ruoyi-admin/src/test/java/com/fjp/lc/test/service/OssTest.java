package com.fjp.lc.test.service;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.utils.OssUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = RuoYiApplication.class)
@ActiveProfiles("dev")
public class OssTest {

    @Autowired
    private OssUtils ossUtils;
    @Test
    public void download() throws Throwable {
        ossUtils.downloadFile("306da8f7f6491046ba86633e4de8240b84微信图片_20220606114231.jpg");
    }
}
