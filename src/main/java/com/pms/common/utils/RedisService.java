package com.pms.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liangrj
 * @since 2023/03/02/ 23:14
 */
@Component
public class RedisService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, (String) value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }




}
