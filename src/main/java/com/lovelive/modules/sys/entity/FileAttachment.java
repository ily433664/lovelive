package com.lovelive.modules.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 文件
 *
 * @author dHe
 */
@Entity(name = "t_file_attachment")
@Table(
        indexes = {
            @Index(name = "idx_fa_md5Hex", columnList = "md5Hex")
        }
)
public class FileAttachment extends BaseEntity {

    private static final long serialVersionUID = 3420071653409103960L;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 附件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件的md5
     */
    private String md5Hex;

    public FileAttachment() {
        super();
    }

    public FileAttachment(Long id) {
        super(id);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
