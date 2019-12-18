package com.lovelive.sys.dao;

import com.lovelive.common.base.BaseDao;
import com.lovelive.sys.entity.ChangeLink;
import org.springframework.stereotype.Repository;

@Repository
public interface IChangeLinkDao extends BaseDao<ChangeLink, String> {

    ChangeLink getChangeLinkById(Long id);

    ChangeLink getChangeLinkByLongURL(String longURL);

    int deleteChangeLinkById(Long id);

}
