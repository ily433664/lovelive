package com.lovelive.sys.dao;

import com.lovelive.sys.entity.LoginLog;
import com.lovelive.sys.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ILogDao {

    int saveLoginLog(LoginLog loginLog);

    int saveOperationLog(OperationLog operLog);

    List<OperationLog> getOperationLog();

}