package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.FileAttachment;

/**
 * 文件 service
 *
 * @author dHe
 */
public interface IFileAttachmentService {

    /**
     * 根据md5获取附件
     *
     * @param md5Hex
     * @return
     */
    FileAttachment getFileAttachmentByMd5Hex(String md5Hex);

    /**
     * 保存附件
     *
     * @param fileAttachment
     * @return
     */
    FileAttachment saveFileAttachment(FileAttachment fileAttachment);

}
