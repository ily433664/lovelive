package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.ChangeLink;
import org.springframework.stereotype.Repository;

@Repository
public interface IChangeLinkDao extends BaseDao<ChangeLink, String> {

    ChangeLink getChangeLinkById(String Id);

    ChangeLink getChangeLinkByLongURL(String longURL);

}
