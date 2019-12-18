package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IAnnexFileDao;
import com.lovelive.sys.entity.AnnexFile;
import com.lovelive.sys.service.IAnnexFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("annexFileService")
@Transactional(rollbackFor = Exception.class)
public class AnnexFileServiceImpl extends BaseService implements IAnnexFileService {

    private IAnnexFileDao annexFileDao;

    @Autowired
    public AnnexFileServiceImpl(IAnnexFileDao annexFileDao) {
        this.annexFileDao = annexFileDao;
    }

    @Override
    public AnnexFile saveAnnexFile(AnnexFile annexFile) {
        return annexFileDao.save(annexFile);
    }
}
