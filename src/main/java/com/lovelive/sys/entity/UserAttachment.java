package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.sys.utils.Global;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * 文件
 *
 * @author dHe
 */
@Entity(name = "t_file_attachment")
@Table(indexes = {
        @Index(name = "idx_fa_md5Hex", columnList = "md5Hex")
}
)
public class UserAttachment extends BaseEntity {

    private static final long serialVersionUID = 4673464880564967866L;

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

}
