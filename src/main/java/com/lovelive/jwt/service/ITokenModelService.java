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
     * 检查 token 是否有效
     *
     * @param tokenModel
     * @return
     */
    public boolean checkToken(TokenModel tokenModel);

    /**
     * 获取 tokenModel
     *
     * @param token
     * @return
     */
    TokenModel getTokenModelByToken(String token);

    /**
     * 保存 tokenModel
     *
     * @param tokenModel
     * @return
     */
    TokenModel saveTokenModel(TokenModel tokenModel);
}
