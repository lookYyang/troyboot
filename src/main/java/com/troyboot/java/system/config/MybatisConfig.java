package com.troyboot.java.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Authour YangYang
 * @Date 2018/6/15 10:01
 */
@Configuration(value = "MybatisConfiguration")
@MapperScan("com.troyboot.java.**.dao")
public class MybatisConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;


}
