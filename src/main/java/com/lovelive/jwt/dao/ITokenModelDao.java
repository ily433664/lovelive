package com.lovelive.jwt.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.jwt.entity.TokenModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenModelDao extends BaseDao<TokenModel, String> {
}
