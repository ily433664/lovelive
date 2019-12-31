package com.lovelive.sys.service;

import com.lovelive.sys.entity.FileAttachment;

/**
 * 文件 service
 *
 * @author dHe
 * @date 2019-5-5
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
