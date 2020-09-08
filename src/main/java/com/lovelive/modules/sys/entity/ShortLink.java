package com.lovelive.modules.sys.entity;

import com.lovelive.common.base.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * 短链接
 *
 * @author dHe
 */
@Entity(name = "t_short_link")
public class ShortLink extends BaseEntity {

    private static final long serialVersionUID = -2252563746367023024L;

    @Length(max = 3000)
    private String longURL;

    private Date createDate;

    private Date updateDate;

    public ShortLink() {
        super();
    }

    public ShortLink(Long id) {
        super(id);
    }

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


