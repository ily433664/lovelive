package com.lovelive.modules.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.modules.sys.entity.FileAttachment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileAttachmentDAO extends BaseDao<FileAttachment, Long> {

    FileAttachment getFileAttachmentByMd5Hex(@Param("md5Hex") String md5Hex);

}
