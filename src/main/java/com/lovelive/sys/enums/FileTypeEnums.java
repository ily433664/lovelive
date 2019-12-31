package com.lovelive.sys.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 文件类型
 *
 * @author dHe
 * @date 2019-12-25
 */
public enum FileTypeEnums {

    // states:1为使用中,0为停止使用
    /**
     * 附件
     */
    ENCLOSURE("1", "附件", 1),
    /**
     * 照片
     */
    PHOTO("2", "照片", 1),
    /**
     * 导入文件
     */
    IMPORT_FILE("9", "导入文件", 1);

    private String value;

    private String name;

    private int states;

    FileTypeEnums(String value, String name, int states) {
        this.value = value;
        this.name = name;
        this.states = states;
    }

    private static Map<String, String> enumVNMap = new LinkedHashMap<>();

    static {
        for (FileTypeEnums enums : FileTypeEnums.values()) {
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
        for (FileTypeEnums enums : FileTypeEnums.values()) {
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
        for (FileTypeEnums enums : FileTypeEnums.values()) {
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
        for (FileTypeEnums enums : FileTypeEnums.values()) {
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
