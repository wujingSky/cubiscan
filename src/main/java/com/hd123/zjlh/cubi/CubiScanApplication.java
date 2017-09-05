/**
 * 版权所有(C)，LHWMS项目组，2017，所有权利保留。
 * 
 * 项目名：	cubi
 * 文件名：	CubiScanApplication.java
 * 模块说明：	
 * 修改历史：
 * 2017年8月21日 - Jing - 创建。
 */
package com.hd123.zjlh.cubi;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hd123.zjlh.cubi.service.impl.CubiScanProperties;

/**
 * @author Jing
 *
 */
@SpringBootApplication
@MapperScan("com.hd123.zjlh.cubi.service.dao")
@EnableTransactionManagement
@EnableConfigurationProperties({CubiScanProperties.class})  
public class CubiScanApplication {
    protected static Logger logger=LoggerFactory.getLogger(CubiScanApplication.class);

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new DataSource();
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/com/hd123/zjlh/cubi/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    public static void main(String[] args) {
        SpringApplication.run(CubiScanApplication.class, args);
        logger.info("CubiScan Start Success");
    }
}
