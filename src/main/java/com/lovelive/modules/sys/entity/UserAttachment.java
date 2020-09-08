package com.lovelive.modules.sys.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.modules.sys.utils.Global;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * 文件
 *
 * @author dHe
 */
@Entity(name = "t_user_attachment")
@Table()
public class UserAttachment extends BaseEntity {

    private static final long serialVersionUID = 3970471775438467628L;

    /**
     * 文件
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private FileAttachment fileAttachment;

    /**
     * 文件类型
     * FileTypeEnums
     */
    private String fileType;

    /**
     * 上传者
     */
    private String userId;

    /**
     * 下载文件时显示的文件名
     */
    private String fileName;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件描述
     */
    private String description;

    /**
     * 该附件是否公开
     */
    private boolean publicly = false;

    /**
     * 创建文件目录
     */
    public boolean createFileDirectory() {
        if (StringUtils.isNotBlank(this.fileAttachment.getFilePath())) {
            return new File(Global.getFilePath() + this.fileAttachment.getFilePath()).getParentFile().mkdirs();
        } else {
            return false;
        }
    }

    public UserAttachment() {
        super();
    }

    public UserAttachment(Long id) {
        super(id);
    }

    public FileAttachment getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(FileAttachment fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublicly() {
        return publicly;
    }

    public void setPublicly(boolean publicly) {
        this.publicly = publicly;
    }
}
