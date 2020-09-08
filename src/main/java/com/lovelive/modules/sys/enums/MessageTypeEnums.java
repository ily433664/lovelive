package com.lovelive.modules.sys.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 消息类型
 *
 * @author dHe
 */
public enum MessageTypeEnums {

    // states:1为使用中,0为停止使用
    /**
     * 系统消息
     */
    SYSTEM("system", "系统消息", 1),
    /**
     * 邮件
     */
    EMAIL("email", "邮件", 1),
    /**
     * 微信
     */
    WECHAT("wechat", "微信", 1),
    /**
     * 短信
     */
    SMS("sms", "短信", 1);

    private String value;

    private String name;

    private int states;

    MessageTypeEnums(String value, String name, int states) {
        this.value = value;
        this.name = name;
        this.states = states;
    }

    private static Map<String, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (MessageTypeEnums enums : MessageTypeEnums.values()) {
            if (enums.states == 1) {
                enumVNMap.put(enums.getValue(), enums.getName());
            }
        }
    }

    /**
     * 获取实体类的状态集合, value-name
     */
    public static Map<String, String> getEnumVNMap() {
        return enumVNMap;
    }

    private static Map<String, String> enumNVMap = new LinkedHashMap<>();

    static {
        for (MessageTypeEnums enums : MessageTypeEnums.values()) {
            if (enums.states == 1) {
                enumNVMap.put(enums.getName(), enums.getValue());
            }
        }
    }

    /**
     * 获取实体类的状态集合, name-value
     */
    public Map<String, String> getEnumNVMap() {
        return enumNVMap;
    }

    /**
     * 根据 value 获取 name
     */
    public String getNameOfValue(String value) {
        for (MessageTypeEnums enums : MessageTypeEnums.values()) {
            if (enums.getValue().equals(value)) {
                return enums.getName();
            }
        }
        return null;
    }

    /**
     * 根据 name 获取 value
     */
    public String getValueOfName(String statusName) {
        for (MessageTypeEnums enums : MessageTypeEnums.values()) {
            if (enums.getName().equals(statusName)) {
                return enums.getValue();
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
