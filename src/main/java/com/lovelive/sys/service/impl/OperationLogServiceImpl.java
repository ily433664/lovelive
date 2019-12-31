package com.lovelive.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.SQLHelper;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.sys.dao.IOperationLogDao;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service("operationLogService")
@Transactional(rollbackFor = Exception.class)
public class OperationLogServiceImpl extends BaseService implements IOperationLogService {

    private IOperationLogDao operationLogDao;

    @Autowired
    public OperationLogServiceImpl(IOperationLogDao operationLogDao) {
        this.operationLogDao = operationLogDao;
    }

    @Override
    public OperationLog getOperationLog(String id) {
        return operationLogDao.getOperationLogById(id);
    }

    @Override
    public OperationLog saveOperationLog(OperationLog operationLog) {
        return operationLogDao.save(operationLog);
    }

    @Override
    public Page<OperationLog> find(OperationLog operationLog) {

        Specification<OperationLog> specification = new Specification<OperationLog>() {
            @Override
            public Predicate toPredicate(Root<OperationLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                if (StringUtils.isNotEmpty(operationLog.getOperAccount())) {
                    Predicate predicate = criteriaBuilder.or(
                            criteriaBuilder.like(root.get("operAccount").as(String.class), SQLHelper.containsLikeValue(operationLog.getOperAccount())),
                            criteriaBuilder.like(root.get("operName").as(String.class), SQLHelper.containsLikeValue(operationLog.getOperAccount()))
                    );
                    predicateList.add(predicate);
                }

                if (StringUtils.isNotEmpty(operationLog.getOperIP())) {
                    Predicate predicate = criteriaBuilder.like(root.get("operIP").as(String.class), SQLHelper.endsWithLikeValue(operationLog.getOperIP()));
                    predicateList.add(predicate);
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[]{}));
            }
        };

        Pageable pageable = PageRequest.of(0, 10, Sort.by("createTime").descending().and(Sort.by("id").descending()));

        return operationLogDao.findAll(specification, pageable);
    }

}
