package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典
 *
 * @author dHe
 * @date 2019-4-29
 */
@Entity
@Table(name = "t_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = -551758085086071338L;

    /**
     * 类型
     */
    private String type;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 说明
     */
    @Lob
    private String description;

    /**
     * 上级字典
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Dict parent;

    /**
     * 下级字典
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Dict> dicts = new ArrayList<>();

    public Dict() {
        super();
    }

    public Dict(String id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dict getParent() {
        return parent;
    }

    public void setParent(Dict parent) {
        this.parent = parent;
    }

    public List<Dict> getDicts() {
        return dicts;
    }

    public void setDicts(List<Dict> dicts) {
        this.dicts = dicts;
    }
}
