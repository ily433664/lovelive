package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.CacheUtils;
import com.lovelive.common.uitls.CollectionUtils;
import com.lovelive.sys.dao.IMailConfigDao;
import com.lovelive.sys.entity.MailConfig;
import com.lovelive.sys.service.IMailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service("mailConfigService")
@Transactional(rollbackFor = Exception.class)
public class MailConfigServiceImpl extends BaseService implements IMailConfigService {

    private IMailConfigDao mailConfigDao;

    @Autowired
    public MailConfigServiceImpl(IMailConfigDao mailConfigDao) {
        this.mailConfigDao = mailConfigDao;
    }

    @Override
    public MailConfig getMailConfig() {

        Specification<MailConfig> specification = new Specification<MailConfig>() {
            @Override
            public Predicate toPredicate(Root<MailConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE);
                predicateList.add(predicate);

                return criteriaBuilder.and(predicateList.toArray(new Predicate[]{}));
            }
        };

        Pageable pageable = PageRequest.of(0, 1, Sort.by("updateTime").descending());

        List<MailConfig> list = mailConfigDao.findAll(specification, pageable).getContent();
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public MailConfig saveMailConfig(MailConfig mailConfig) {
        mailConfig = mailConfigDao.save(mailConfig);
        CacheUtils.remove(CacheUtils.MAIL_CONFIG_CACHE);
        return mailConfig;
    }
}
