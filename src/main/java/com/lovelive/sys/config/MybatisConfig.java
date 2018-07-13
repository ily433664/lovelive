package com.lovelive.sys.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author dhe
 * @Description Mybatis配置
 * @date 2018年3月21日
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        //添加配置，也可以指定文件路径
        Properties p = new Properties();
        p.setProperty("reasonable", "true");//分页合理化参数，默认值为false。当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页
        p.setProperty("offsetAsPageNum", "true");//默认值为false，此参数对于RowBounds作为分页参数有效。当此参数设置为时true，offset参数in RowBounds将用作pageNum
        p.setProperty("autoRuntimeDialect", "true");//默认值为 false。设置为 true 时，允许在运行时根据多数据源自动识别对应方言的分页 （不支持自动选择sqlserver2012，只能使用sqlserver）
        p.setProperty("rowBoundsWithCount", "true");//使用 PageInfo 类，你需要设置该参数为 true
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
