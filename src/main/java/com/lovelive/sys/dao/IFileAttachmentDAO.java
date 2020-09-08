package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.FileAttachment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileAttachmentDAO extends BaseDao<FileAttachment, Long> {

    FileAttachment getFileAttachmentByMd5Hex(@Param("md5Hex") String md5Hex);

}
