package com.lovelive.sys.enums;

import com.lovelive.common.uitls.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 消息模板类型
 *
 * @author dHe
 */

public enum MessageTemplateTypeEnums {

    /**
     * 注册
     */
    REGISTER("REGISTER", "注册"),
    /**
     * 测试
     */
    test("test", "测试"),;

    private String value;

    private String name;

    private static Map<String, String> codeNameMap;

    private static Map<String, String> nameCodeMap;

    MessageTemplateTypeEnums(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> getCodeNameMap() {
        if (codeNameMap == null || codeNameMap.size() == 0) {
            codeNameMap = new LinkedHashMap<>();
            for (MessageTemplateTypeEnums enums : values()) {
                codeNameMap.put(enums.getValue(), enums.getName());
            }
        }
        return codeNameMap;
    }

    public static String getName(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return getCodeNameMap().get(value);
    }

    public static Map<String, String> getNameCodeMap() {
        if (nameCodeMap == null || nameCodeMap.size() == 0) {
            nameCodeMap = new LinkedHashMap<>();
            for (MessageTemplateTypeEnums enums : values()) {
                nameCodeMap.put(enums.getName(), enums.getValue());
            }
        }
        return nameCodeMap;
    }

    public static String getValue(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return getNameCodeMap().get(name);
    }
}
