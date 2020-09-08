package com.lovelive.modules.sys.service.impl;

import com.lovelive.common.base.BaseService;
import com.lovelive.common.uitls.SQLHelper;
import com.lovelive.common.uitls.StringUtils;
import com.lovelive.modules.sys.dao.IOperationLogDAO;
import com.lovelive.modules.sys.entity.OperationLog;
import com.lovelive.modules.sys.service.IOperationLogService;
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

    private IOperationLogDAO operationLogDAO;

    @Autowired
    public OperationLogServiceImpl(IOperationLogDAO operationLogDAO) {
        this.operationLogDAO = operationLogDAO;
    }

    @Override
    public OperationLog getOperationLog(Long id) {
        return operationLogDAO.getOperationLogById(id);
    }

    @Override
    public OperationLog saveOperationLog(OperationLog operationLog) {
        return operationLogDAO.save(operationLog);
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

        return operationLogDAO.findAll(specification, pageable);
    }

}
