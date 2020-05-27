package com.wechat.web.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mjg on 2018/4/28.
 */
@Configuration
@AutoConfigureAfter({MyBatisConfig.class})
public class MybatisMapperScanConfig {

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    String daoPkgName = "com.wechat.dao";
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    mapperScannerConfigurer.setBasePackage(daoPkgName);
    return mapperScannerConfigurer;
  }
}
