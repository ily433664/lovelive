package com.lovelive.jwt.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.CacheUtils;
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
    public boolean checkToken(TokenModel tokenModel) {
        //TODO  验证是否有效

        if (tokenModel == null) {
            return false;
        }

        return true;
    }

    @Override
    public TokenModel getTokenModelByToken(String token) {
        // 从缓存获取 TokenModel，如果为空则从数据库查询
        TokenModel tokenModel = (TokenModel) CacheUtils.get(CacheUtils.TOKEN_CACHE, token);
        if (tokenModel != null) {
            // 刷新缓存的过期时间
            CacheUtils.expire(CacheUtils.TOKEN_CACHE, tokenModel.getToken());
            return tokenModel;
        } else {
            tokenModel = tokenModelDao.getTokenModelByToken(token);
            if (tokenModel != null) {
                // 将 TokenModel 存入缓存
                CacheUtils.put(CacheUtils.TOKEN_CACHE, tokenModel.getToken(), tokenModel);
            }
        }
        return tokenModel;
    }

    @Override
    public TokenModel saveTokenModel(TokenModel tokenModel) {
        tokenModel = tokenModelDao.save(tokenModel);
        // 将 TokenModel 存入缓存
        CacheUtils.put(CacheUtils.TOKEN_CACHE, tokenModel.getToken(), tokenModel);
        return tokenModel;
    }
}
