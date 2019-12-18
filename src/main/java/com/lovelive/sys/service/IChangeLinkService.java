package com.lovelive.sys.service;

import com.lovelive.sys.entity.ChangeLink;

public interface IChangeLinkService {

    /**
     * 根据id获取短链接对象
     *
     * @param id
     * @return
     */
    ChangeLink getChangeLink(Long id);

    /**
     * 根据longUrl获取短链接对象
     *
     * @param longUrl
     * @return
     */
    ChangeLink getChangeLinkByLongURL(String longUrl);

    /**
     * 保存短链接对象
     *
     * @param changeLink
     * @return
     */
    ChangeLink saveChangeLink(ChangeLink changeLink);

    /**
     * 删除短链接对象
     *
     * @param id
     */
    int deleteChangeLink(Long id);
}
