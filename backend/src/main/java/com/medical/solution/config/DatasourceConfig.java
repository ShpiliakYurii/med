package com.medical.solution.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

@Configuration
@EnableTransactionManagement
public class DatasourceConfig {
    @Value("${spring.datasource.url}")
    private String CONN_STR;
    @Value("${spring.datasource.driver-class-name}")
    private String DRIVER_CLASS;
    @Value("${spring.datasource.tomcat.max-active}")
    private int MAX_ACTIVE;


    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DRIVER_CLASS);
        comboPooledDataSource.setJdbcUrl(CONN_STR);
        comboPooledDataSource.setMaxPoolSize(MAX_ACTIVE);
        return comboPooledDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(ComboPooledDataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
}
