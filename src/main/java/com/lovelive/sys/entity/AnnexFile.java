package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.sys.utils.Global;
import com.lovelive.common.uitls.StringUtils;

import javax.persistence.*;
import java.io.File;

/**
 * @Description 文件
 * @Author dHe
 * @Date 2019/5/5
 */
@Entity
@Table(name = "t_annex_file")
public class AnnexFile extends BaseEntity {

    private static final long serialVersionUID = 1025848265647681225L;

    /**
     * 上传者
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    /**
     * 子目录
     * File.separator + "目录一" + File.separator + "目录二"
     */
    private String subdirectory;

    /**
     * 实际文件名，即保存在服务器里的文件名
     */
    private String fileRealName;

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
     * 附件大小（字节）
     */
    private Long fileSize;

    /**
     * 获取文件路径
     */
    @Transient
    public String getFilePath() {
        if (StringUtils.isNotBlank(this.subdirectory)) {
            File dir = new File(Global.getFilePath() + this.subdirectory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return Global.getFilePath() + this.subdirectory + File.separator + this.fileRealName;
        } else {
            return Global.getFilePath() + File.separator + this.fileRealName;
        }
    }

    public AnnexFile() {
        super();
    }

    public AnnexFile(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubdirectory() {
        return subdirectory;
    }

    public void setSubdirectory(String subdirectory) {
        this.subdirectory = subdirectory;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
