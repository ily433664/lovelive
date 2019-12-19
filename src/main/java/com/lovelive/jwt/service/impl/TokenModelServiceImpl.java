package com.lovelive.jwt.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.jwt.dao.ITokenModelDao;
import com.lovelive.jwt.entity.TokenModel;
import com.lovelive.jwt.service.ITokenModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tokenModelService")
@Transactional(rollbackFor = Exception.class)
public class TokenModelServiceImpl extends BaseService implements ITokenModelService {

    private ITokenModelDao tokenModelDao;

    @Autowired
    public TokenModelServiceImpl(ITokenModelDao tokenModelDao) {
        this.tokenModelDao = tokenModelDao;
    }

    @Override
    public TokenModel saveTokenModel(TokenModel tokenModel) {
        return tokenModelDao.save(tokenModel);
    }
}
