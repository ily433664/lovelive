package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IFileAttachmentDao;
import com.lovelive.sys.entity.FileAttachment;
import com.lovelive.sys.service.IFileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("annexFileService")
@Transactional(rollbackFor = Exception.class)
public class FileAttachmentServiceImpl extends BaseService implements IFileAttachmentService {

    private IFileAttachmentDao fileAttachmentDao;

    @Autowired
    public FileAttachmentServiceImpl(IFileAttachmentDao fileAttachmentDao) {
        this.fileAttachmentDao = fileAttachmentDao;
    }

    @Override
    public FileAttachment getFileAttachmentByMd5Hex(String md5Hex) {
        return fileAttachmentDao.getFileAttachmentByMd5Hex(md5Hex);
    }

    @Override
    public FileAttachment saveFileAttachment(FileAttachment fileAttachment) {
        return fileAttachmentDao.save(fileAttachment);
    }
}
