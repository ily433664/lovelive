package com.lovelive.modules.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.modules.sys.dao.IShortLinkDAO;
import com.lovelive.modules.sys.entity.ShortLink;
import com.lovelive.modules.sys.service.IShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author dHe
 */
@Service("shortLinkService")
public class ShortLinkServiceImpl extends BaseService implements IShortLinkService {

    private IShortLinkDAO shortLinkDAO;

    @Autowired
    public ShortLinkServiceImpl(IShortLinkDAO changeLinkDAO) {
        this.shortLinkDAO = changeLinkDAO;
    }

    public ShortLink getChangeLink(Long id) {
        ShortLink shortLink = shortLinkDAO.getChangeLinkById(id);
        if (shortLink != null) {
            shortLink.setUpdateDate(new Date());
            shortLinkDAO.save(shortLink);
        }
        return shortLink;
    }

    @Override
    public ShortLink getChangeLinkByLongURL(String longUrl) {
        ShortLink shortLink = shortLinkDAO.getChangeLinkByLongURL(longUrl);
        if (shortLink != null) {
            shortLink.setUpdateDate(new Date());
            shortLinkDAO.save(shortLink);
        }
        return shortLink;
    }

    public ShortLink saveChangeLink(ShortLink shortLink) {
        if (StringUtils.isNotBlank(shortLink.getLongURL())) {
            ShortLink temp = shortLinkDAO.getChangeLinkByLongURL(shortLink.getLongURL());
            if (temp != null) {
                shortLink = temp;
            }
        }
        if (shortLink != null) {
            shortLink.setUpdateDate(new Date());
            shortLinkDAO.save(shortLink);
        }
        shortLinkDAO.save(shortLink);
        return shortLink;
    }

    public void deleteChangeLink(Long id) {
        shortLinkDAO.deleteById(id);
    }
}
