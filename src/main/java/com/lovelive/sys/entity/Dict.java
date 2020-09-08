package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 字典
 *
 * @author dHe
 */
@Entity(name = "t_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 7940244544921578962L;

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
     * 简简称
     */
    private String shortShortName;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 英文简称
     */
    private String shortEnName;

    /**
     * 英文简简称
     */
    private String shortShortEnName;

    /**
     * 说明
     */
    @Lob
    private String description;

    /**
     * 上级字典
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Dict parent;

    /**
     * 下级字典
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<Dict> dicts = new LinkedHashSet<>();

    public Dict() {
        super();
    }

    public Dict(Long id) {
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

    public String getShortShortName() {
        return shortShortName;
    }

    public void setShortShortName(String shortShortName) {
        this.shortShortName = shortShortName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getShortEnName() {
        return shortEnName;
    }

    public void setShortEnName(String shortEnName) {
        this.shortEnName = shortEnName;
    }

    public String getShortShortEnName() {
        return shortShortEnName;
    }

    public void setShortShortEnName(String shortShortEnName) {
        this.shortShortEnName = shortShortEnName;
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

    public Set<Dict> getDicts() {
        return dicts;
    }

    public void setDicts(Set<Dict> dicts) {
        this.dicts = dicts;
    }
}
