package com.lovelive.test.dao;

import com.lovelive.test.entity.TestMybatis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITestDao {

    List<TestMybatis> selectTest();
}