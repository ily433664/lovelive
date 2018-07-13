package com.lovelive.sys.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: BaseRecord
 * @Description: Record支持类
 * @date 2018年1月18日 下午5:59:00
 */

public abstract class BaseRecord implements Serializable {

    public static final long serialVersionUID = 2L;
    /**
     * 审核状态
     */
    private Integer auditStatus = RecordStatusEnum.UNSUBMITTED.getCode();
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 最近更新时间
     */
    private Timestamp updateTime;

    protected BaseRecord() {
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

}
