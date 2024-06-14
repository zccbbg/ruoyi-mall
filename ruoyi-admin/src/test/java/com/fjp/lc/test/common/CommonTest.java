package com.fjp.lc.test.common;

import cn.hutool.core.img.Img;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommonTest {
   /* @Test
    public void test3() throws IOException {
        Long start = System.currentTimeMillis();
        log.info("start {}", start);
        Thumbnails.of("D:/build/tt.jpg")
                .scale(1f)
                .outputQuality(0.5f)
                .toFile("D:/build/tt1.jpg");
        log.info("end {}", System.currentTimeMillis() - start);
    }*/

    @Test
    public void testEquals(){
        Integer num1 = 100;
        Integer num2 = 100;

        System.out.println(num1 == num2);      // true，因为对于 Integer 类型，-128 到 127 之间的值会被缓存
        System.out.println(num1.equals(num2)); // true，因为它们的值相同

        Integer num3 = 200;
        Integer num4 = 200;

        System.out.println(num3 == num4);      // false，因为超出了缓存范围，会创建新的对象实例
        System.out.println(num3.equals(num4)); // true，因为它们的值相同
    }

    @Test
    public void test4() throws IOException {
        String f1 = "D:/build/tt.jpg";
        String f2 = "D:/build/tt2.jpg";
        Long start = System.currentTimeMillis();
        log.info("start {}", start);
        ImgUtil.scale(new File(f1), new File(f2), .1f);
        log.info("end {}", System.currentTimeMillis() - start);
    }
    @Test
    public void test5() throws IOException {
        String f1 = "D:/build/tt.jpg";
        String f2 = "D:/build/tt3.jpg";
        Long start = System.currentTimeMillis();
        log.info("start {}", start);
        Img.from(FileUtil.file(f1))
                .setQuality(1)//压缩比率
                .write(FileUtil.file(f2));
        log.info("end {}", System.currentTimeMillis() - start);
    }

    @Test
    public void testTimestampt(){
        Instant tsObj = Instant.now();

        long secs = tsObj.getEpochSecond();

        System.out.println(secs);
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://bmfw.www.gov.cn/bjww/interface/interfaceJson";

        String key = "3C502C97ABDA40D0A60FBEE50FAAD1DA";
        Long timestamp = Instant.now().getEpochSecond();
        String token = "23y0ufFl5YxIyGrI8hWRUZmKkvtSjLQA";
        String nonce ="123456789abcdefg";
        String passid = "zdww";
        String tempString = timestamp + token + nonce + timestamp;

        String signatureHeader = DigestUtils.sha256Hex(tempString).toUpperCase();
        System.out.println(signatureHeader);
        tempString = timestamp + "fTN2pfuisxTavbTuYVSsNJHetwq5bJvC" + "QkjjtiLM2dCratiA" + timestamp;
        String zdwwsignature = DigestUtils.sha256Hex(tempString).toUpperCase();
        System.out.println(zdwwsignature);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-wif-nonce","QkjjtiLM2dCratiA");
        headers.set("x-wif-paasid","smt-application");
        headers.set("x-wif-signature",zdwwsignature);
        headers.set("x-wif-timestamp",timestamp.toString());
        headers.set("Origin","http://bmfw.www.gov.cn");
        headers.set("Referer","http://bmfw.www.gov.cn/yqfxdjcx/risk.html");

        Map<String,String> map = new HashMap<String,String>();
        map.put("appId","NcApplication");
        map.put("paasHeader",passid);
        map.put("timestampHeader",timestamp.toString());
        map.put("nonceHeader",nonce);
        map.put("signatureHeader",signatureHeader);
        map.put("key",key);

        String json= JSON.toJSONString(map);
        System.out.println(json);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);

        String ans = restTemplate.postForObject(url, entity, String.class);
        System.out.println(ans);

    }
}
