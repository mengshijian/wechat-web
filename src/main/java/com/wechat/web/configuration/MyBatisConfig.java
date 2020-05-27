package com.wechat.web.configuration;


import com.alibaba.druid.pool.DruidDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @Author Charles
 * @Created 2017/8/16 9:35
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DataSourceProperties.class})
public class MyBatisConfig {

  private static final String entityName = "com.wechat.entity";

  private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

  @Autowired
  private DataSourceProperties properties;


  @Bean(name = "dataSource")
  @Qualifier("dataSource")
  public DataSource dataSource() {
    DruidDataSource ds = new DruidDataSource();
    logger.debug("DruidDataSource开始连接数据源...");
    ds.setDriverClassName(this.properties.getDriverClass());
    ds.setUrl(this.properties.getUrl());
    ds.setUsername(this.properties.getUsername());
    ds.setPassword(this.properties.getPassword());
    ds.setMaxActive(this.properties.getMaxActive());
    ds.setMaxWait(this.properties.getMaxWait());
    ds.setInitialSize(this.properties.getInitialSize());
    ds.setValidationQuery(this.properties.getValidationQuery());
    ds.setPoolPreparedStatements(this.properties.isPoolPreparedStatements());
    ds.setMaxPoolPreparedStatementPerConnectionSize(
      this.properties.getMaxPoolPreparedStatementPerConnectionSize());
    ds.setTestWhileIdle(true);
    ds.setTestOnBorrow(false);
    ds.setTestOnReturn(false);
    try {
      ds.setFilters(this.properties.getFilters());
    } catch (SQLException e) {
      logger.error("发生异常：", e);
    }
    ds.setConnectionProperties(this.properties.getConnectionProperties());
    return ds;
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
    throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage(entityName);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    try {
      sqlSessionFactoryBean
        .setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
      SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
      if (sqlSessionFactory != null) {
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
      }
      return sqlSessionFactory;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  @Bean
  public PlatformTransactionManager annotationDrivenTransactionManager(
    @Qualifier("dataSource") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}