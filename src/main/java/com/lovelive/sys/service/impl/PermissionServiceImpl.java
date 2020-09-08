package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.CacheUtils;
import com.lovelive.sys.dao.IPermissionDAO;
import com.lovelive.sys.entity.Permission;
import com.lovelive.sys.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service("permissionService")
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends BaseService implements IPermissionService {

    private IPermissionDAO permissionDAO;

    @Autowired
    public PermissionServiceImpl(IPermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    @Override
    public Permission savePermission(Permission permission) {
        permission = permissionDAO.save(permission);
        CacheUtils.remove(CacheUtils.PERMISSION_CACHE);
        return permission;
    }

    @Override
    public List<Permission> findAllNormal() {

        Specification<Permission> specification = new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE);
                predicateList.add(predicate);

                return criteriaBuilder.and(predicateList.toArray(new Predicate[]{}));
            }
        };

        return permissionDAO.findAll(specification, Sort.by("sequence").ascending());
    }
}
