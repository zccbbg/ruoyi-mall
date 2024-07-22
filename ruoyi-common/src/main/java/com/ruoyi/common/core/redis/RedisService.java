package com.ruoyi.common.core.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisCache redisCache;

    public void setMatchList(Long userId, List<Long> userIds) {
        String key = RedisKeys.MATCH_LIST_OF + userId;
        redisCache.setCacheList(key, userIds);
        redisCache.expire(key, 7, TimeUnit.DAYS);
    }

    public List<Long> getMatchList(Long userId) {
        String key = RedisKeys.MATCH_LIST_OF + userId;
        return redisCache.getCacheList(key);
    }

    public String getAddressList() {
        String key = RedisKeys.ADDRESS_LIST_KEY;
        return redisCache.getCacheObject(key);
    }

    public void setAddressList(String list) {
        String key = RedisKeys.ADDRESS_LIST_KEY;
        redisCache.setCacheObject(key,list);
    }

    public void setVerifyCode(String code) {
        String key = RedisKeys.VERIFY_CODE + code;
        redisCache.setCacheObject(key, code);
        redisCache.expire(key, 5, TimeUnit.MINUTES);
    }

    public void deleteVerifyCode(String code) {
        redisCache.deleteObject(RedisKeys.VERIFY_CODE + code);
    }

    public String getVerifyCode(String code) {
        return redisCache.getCacheObject(RedisKeys.VERIFY_CODE + code);
    }

    public String getWechatToken() {
        return redisCache.getCacheObject(RedisKeys.WECHAT_ACCESS_TOKEN);
    }

    public void setWechatToken(String token) {
        redisCache.setCacheObject(RedisKeys.WECHAT_ACCESS_TOKEN, token, 100, TimeUnit.MINUTES);
    }

    public void setQrCode(String code, String scene) {
        redisCache.setCacheObject(RedisKeys.WECHAT_QR_CODE + scene, code, 30, TimeUnit.DAYS);
    }

    public String getQrCode(String scene) {
        return redisCache.getCacheObject(RedisKeys.WECHAT_QR_CODE + scene);
    }

    interface RedisKeys {
        String MATCH_LIST_OF = "MATCH_LIST_OF_";
        String ADDRESS_LIST_KEY = "ADDRESS_LIST_KEY_";

        String WECHAT_ACCESS_TOKEN = "WECHAT_ACCESS_TOKEN_";
        String WECHAT_QR_CODE = "WECHAT_QR_CODE_";
        String VERIFY_CODE = "VERIFY_CODE:";
    }

    /**
     * redis实现分布式锁 --- 上锁
     *
     * @param key
     * @param jobInfo
     * @param lockSecond
     * @return
     * @throws Exception
     */
    public void lock(String key, String jobInfo, Integer lockSecond) throws Exception {
        String existJobInfo = redisCache.getCacheObject(key);
        if (StringUtils.isNotEmpty(existJobInfo)) {
            log.info("获取锁失败: redisKey: {}, existJobInfo: {}", key, existJobInfo);
            throw new Exception("请不要反复提交订单！");
        }
        redisCache.setCacheObject(key, jobInfo, lockSecond, TimeUnit.SECONDS);
    }

    /**
     * redis实现分布式锁 --- 解锁
     *
     * @param key
     * @param jobInfo
     * @throws Exception
     */
    public void unLock(String key, String jobInfo) throws Exception {
        String existJobInfo = redisCache.getCacheObject(key);
        if (jobInfo.equals(existJobInfo)) {
            redisCache.deleteObject(key);
        } else {
            throw new Exception(String.format("释放锁异常: redisKey: %s, existJobInfo: %s, jobInfo: %s", key, existJobInfo, jobInfo));
        }
    }
}
