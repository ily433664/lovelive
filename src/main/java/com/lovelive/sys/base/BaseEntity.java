package com.lovelive.sys.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: BaseEntity
 * @Description: Entity支持类
 * @date 2018年1月18日 下午5:58:48
 */

public abstract class BaseEntity implements Serializable {

    public static final long serialVersionUID = 1L;
    /**
     * 状态
     */
    private Integer status = EntityStatusEnum.ENABLED.getCode();
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 最近更新时间
     */
    private Timestamp updateTime;

    protected BaseEntity() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

}
