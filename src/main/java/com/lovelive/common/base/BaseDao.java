package com.lovelive.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description Dao支持类
 * @Author dhe
 * @Date 2019/4/26
 */
@NoRepositoryBean
public interface BaseDao<T, ID> extends JpaRepository<T, ID>, PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor {
}
