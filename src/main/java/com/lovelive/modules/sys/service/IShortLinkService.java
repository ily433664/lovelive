package com.lovelive.modules.sys.service;

import com.lovelive.modules.sys.entity.ShortLink;

/**
 * 短链接 service
 *
 * @author dHe
 */
public interface IShortLinkService {

    /**
     * 根据id获取短链接对象
     *
     * @param id
     * @return
     */
    ShortLink getChangeLink(Long id);

    /**
     * 根据longUrl获取短链接对象
     *
     * @param longUrl
     * @return
     */
    ShortLink getChangeLinkByLongURL(String longUrl);

    /**
     * 保存短链接对象
     *
     * @param shortLink
     * @return
     */
    ShortLink saveChangeLink(ShortLink shortLink);

    /**
     * 删除短链接对象
     *
     * @param id
     */
    void deleteChangeLink(Long id);
}
