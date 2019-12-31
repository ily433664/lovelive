package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.sys.utils.Global;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.File;

/**
 * 文件
 *
 * @author dHe
 * @date 2019-5-5
 */
@Entity
@Table(
        name = "t_file_attachment",
        indexes = {
                @Index(name = "idx_fileAttachment_md5Hex", columnList = "md5Hex")
        }
)
public class FileAttachment extends BaseEntity {

    private static final long serialVersionUID = -2461342180713282327L;

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
     * 文件路径
     */
    private String filePath;

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
    private boolean publicly;

    /**
     * 附件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件的md5
     */
    private String md5Hex;

    /**
     * 创建文件目录
     */
    public boolean createFileDirectory() {
        if (StringUtils.isNotBlank(this.filePath)) {
            return new File(Global.getFilePath() + this.filePath).getParentFile().mkdirs();
        } else {
            return false;
        }
    }

    public FileAttachment() {
        super();
    }

    public FileAttachment(String id) {
        this.id = id;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMd5Hex() {
        return md5Hex;
    }

    public void setMd5Hex(String md5Hex) {
        this.md5Hex = md5Hex;
    }
}
