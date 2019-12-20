package com.lovelive.sys.service;

import java.util.List;

/**
 * redis service
 *
 * @author dhe
 * @date 2019-12-18
 */
public interface IRedisService {

    void setStringValue(String key, String value);

    void setStringValue(String key, String value, Long time);

    void setListValue(String key, List<String> listValue);

    void setListValue(String key, List<String> listValue, Long time);

    String getStringValue(String key);

    List<String> getListString(String key, int start, int end);

    void setObject(String key, Object value, Long time);

}
