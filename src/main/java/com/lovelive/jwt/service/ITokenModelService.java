package com.lovelive.jwt.service;

import com.lovelive.jwt.entity.TokenModel;

/**
 * token service
 *
 * @author dHe
 * @date 2019-12-19
 */
public interface ITokenModelService {

    /**
     * 保存tokenModel
     *
     * @param tokenModel
     * @return
     */
    TokenModel saveTokenModel(TokenModel tokenModel);
}
