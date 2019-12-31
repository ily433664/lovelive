package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * 短链接
 *
 * @author dHe
 * @date 2019-12-15
 */
@Entity
@Table(name = "t_change_link")
public class ChangeLink extends BaseEntity {

    private static final long serialVersionUID = 3713775289425929603L;

    @Length(max = 3000)
    private String longURL;

    private Date createDate;

    private Date updateDate;

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}


