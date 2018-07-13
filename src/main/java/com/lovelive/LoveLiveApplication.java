package com.lovelive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("xxx.mapper")
@SpringBootApplication
@EnableCaching
public class LoveLiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveLiveApplication.class, args);
    }
}
