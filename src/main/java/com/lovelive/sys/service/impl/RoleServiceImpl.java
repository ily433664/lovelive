package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.CacheUtils;
import com.lovelive.sys.dao.IRoleDao;
import com.lovelive.sys.entity.Role;
import com.lovelive.sys.service.IRoleService;
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

@Service("roleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService implements IRoleService {

    private IRoleDao roleDao;

    @Autowired
    public RoleServiceImpl(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role saveRole(Role role) {
        role = roleDao.save(role);
        CacheUtils.remove(CacheUtils.ROLE_CACHE);
        return role;
    }

    @Override
    public List<Role> findAllNormal() {

        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                Predicate predicate = criteriaBuilder.equal(root.get("deleted"), Boolean.FALSE);
                predicateList.add(predicate);

                return criteriaBuilder.and(predicateList.toArray(new Predicate[]{}));
            }
        };

        return roleDao.findAll(specification, Sort.by("sequence").ascending());
    }
}
