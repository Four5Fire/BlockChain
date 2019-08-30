package com.blockChain.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:conf/mysql.properties"})
public class MySQLConfig {

    @Value(value="${blockchain.mysql.jdbc.url}")
    private String jdbcUrl;

    @Value(value="${blockchain.mysql.jdbc.user}")
    private String jdbcUser;

    @Value(value = "${blockchain.mysql.jdbc.password}")
    private String jdbcPassword;

    @Value(value = "${blockchain.mysql.jdbc.driver}")
    private String jdbcDriver;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }
}