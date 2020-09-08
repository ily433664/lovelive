package com.lovelive.modules.sys.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.modules.sys.enums.MessageTemplateTypeEnums;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

/**
 * 消息模板
 *
 * @author dHe
 */
@Entity(name = "t_message_template")
public class MessageTemplate extends BaseEntity {

    private static final long serialVersionUID = -3485228860976537262L;

    /**
     * 类型
     * MessageTemplateTypeEnums
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
     * 内容
     **/
    @Lob
    private String content;

    public MessageTemplate() {
        super();
    }

    public MessageTemplate(Long id) {
        super(id);
    }

    @Transient
    public String getMsgTemplateName() {
        return MessageTemplateTypeEnums.getName(code);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
