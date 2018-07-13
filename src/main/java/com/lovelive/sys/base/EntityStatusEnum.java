package com.lovelive.sys.base;

import java.util.LinkedHashMap;
import java.util.Map;

public enum EntityStatusEnum {

    /**
     * 状态：正常
     */
    NORMAL(0, "正常"),
    /**
     * 状态：启用
     */
    ENABLED(1, "启用"),
    /**
     * 状态：锁定
     */
    LOCKED(2, "锁定"),
    /**
     * 状态：停用
     */
    DISABLED(3, "停用"),
    /**
     * 状态：删除
     */
    DELETED(9, "删除");

    private Integer code;

    private String name;

    EntityStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static Map<Integer, String> auditStatusCNMap = new LinkedHashMap<>();

    static {
        for (EntityStatusEnum status : EntityStatusEnum.values()) {
            auditStatusCNMap.put(status.getCode(), status.getName());
        }
    }

    /**
     * 获取实体类的状态集合, code-name
     */
    public static Map<Integer, String> getAuditStatusCNMap() {
        return auditStatusCNMap;
    }

    private static Map<String, Integer> auditStatusNCMap = new LinkedHashMap<>();

    static {
        for (EntityStatusEnum status : EntityStatusEnum.values()) {
            auditStatusNCMap.put(status.getName(), status.getCode());
        }
    }

    /**
     * 获取实体类的状态集合, value-code
     */
    public Map<String, Integer> getAuditStatusVCMap() {
        return auditStatusNCMap;
    }

    /**
     * 根据code获取name
     */
    public String getNameOfCode(String statusCode) {
        for (EntityStatusEnum entityStatus : EntityStatusEnum.values()) {
            if (entityStatus.getCode().equals(statusCode)) {
                return entityStatus.getName();
            }
        }
        return null;
    }

    /**
     * 根据name获取code
     */
    public Integer getStatusOfName(String statusName) {
        for (EntityStatusEnum entityStatus : EntityStatusEnum.values()) {
            if (entityStatus.getName().equals(statusName)) {
                return entityStatus.getCode();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
