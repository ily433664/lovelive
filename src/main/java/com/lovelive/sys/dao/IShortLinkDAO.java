package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.ShortLink;
import org.springframework.stereotype.Repository;

@Repository
public interface IShortLinkDAO extends BaseDao<ShortLink, Long> {

    ShortLink getChangeLinkById(Long Id);

    ShortLink getChangeLinkByLongURL(String longURL);

}
