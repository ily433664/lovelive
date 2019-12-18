package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.sys.dao.IChangeLinkDao;
import com.lovelive.sys.entity.ChangeLink;
import com.lovelive.sys.service.IChangeLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author dHe
 */
@Service("changeLinkService")
public class ChangeLinkServiceImpl extends BaseService implements IChangeLinkService {

    @Autowired
    private IChangeLinkDao changeLinkDao;

    public ChangeLink getChangeLink(Long id) {
        ChangeLink changeLink = changeLinkDao.getChangeLinkById(id);
        if (changeLink != null) {
            changeLink.setUpdateDate(new Date());
            changeLinkDao.save(changeLink);
        }
        return changeLink;
    }

    @Override
    public ChangeLink getChangeLinkByLongURL(String longUrl) {
        ChangeLink changeLink = changeLinkDao.getChangeLinkByLongURL(longUrl);
        if (changeLink != null) {
            changeLink.setUpdateDate(new Date());
            changeLinkDao.save(changeLink);
        }
        return changeLink;
    }

    public ChangeLink saveChangeLink(ChangeLink changeLink) {
        if (StringUtils.isNotBlank(changeLink.getLongURL())) {
            ChangeLink temp = changeLinkDao.getChangeLinkByLongURL(changeLink.getLongURL());
            if (temp != null) {
                return temp;
            }
        }
        if (StringUtils.isNotBlank(changeLink.getId())) {
            changeLink.setCreateDate(new Date());
        }
        changeLinkDao.save(changeLink);
        return changeLink;
    }

    public int deleteChangeLink(Long id) {
        return changeLinkDao.deleteChangeLinkById(id);
    }
}
