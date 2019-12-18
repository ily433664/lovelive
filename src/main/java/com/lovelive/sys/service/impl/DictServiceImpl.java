package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.sys.dao.IDictDao;
import com.lovelive.sys.entity.Dict;
import com.lovelive.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dictService")
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl extends BaseService implements IDictService {

    private IDictDao dictDao;

    @Autowired
    public DictServiceImpl(IDictDao dictDao) {
        this.dictDao = dictDao;
    }

    @Override
    public Dict saveDict(Dict dict) {
        return dictDao.save(dict);
    }
}
