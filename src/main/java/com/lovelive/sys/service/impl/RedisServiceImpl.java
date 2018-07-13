package com.lovelive.sys.service.impl;

import com.lovelive.sys.service.IRedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements IRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    private RedisTemplate redisTemplate;

    @Autowired
    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate, RedisTemplate redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    public void setStringValue(String key, String value) {
        this.setStringValue(key, value, null);
    }

    public void setStringValue(String key, String value, Long time) {
        this.setObject(key, value, time);
    }

    public void setListValue(String key, List<String> listValue) {
        this.setObject(key, listValue, null);
    }

    public void setListValue(String key, List<String> listValue, Long time) {
        this.setObject(key, listValue, time);
    }

    public String getStringValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public List<String> getListString(String key, int start, int end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    @SuppressWarnings("unchecked")
    public void setObject(String key, Object value, Long time) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }
        if (value instanceof String) {
            String strValue = (String) value;
            stringRedisTemplate.opsForValue().set(key, strValue);
            if (time != null) {
                stringRedisTemplate.opsForValue().set(key, strValue, time, TimeUnit.SECONDS);
            }
            return;
        }
        if (value instanceof List) {
            List<String> listValue = (List<String>) value;
            for (String strValue : listValue) {
                stringRedisTemplate.opsForList().rightPopAndLeftPush(key, strValue);
                if (time != null) {
                    stringRedisTemplate.opsForList().rightPopAndLeftPush(key, strValue, time, TimeUnit.SECONDS);
                }
            }
        }
    }
}
