package com.lovelive.sys.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;

public class TestMybatis extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1873236660763355298L;
    private String id;
    private String code;
    private String name;

    public TestMybatis() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
