package com.lovelive.modules.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.modules.sys.dao.IDictDAO;
import com.lovelive.modules.sys.entity.Dict;
import com.lovelive.modules.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dictService")
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl extends BaseService implements IDictService {

    private IDictDAO dictDAO;

    @Autowired
    public DictServiceImpl(IDictDAO dictDAO) {
        this.dictDAO = dictDAO;
    }

    @Override
    public Dict saveDict(Dict dict) {
        return dictDAO.save(dict);
    }
}
