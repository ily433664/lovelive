package com.lovelive.test.service.impl;

import com.lovelive.test.dao.ITestDao;
import com.lovelive.test.entity.TestMybatis;
import com.lovelive.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl implements ITestService {

    @Autowired
    public ITestDao testDao;

    @Override
    public List<TestMybatis> selectTest() {
        return testDao.selectTest();
    }
}
