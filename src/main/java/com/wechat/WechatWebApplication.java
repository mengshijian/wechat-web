package com.wechat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by mjg on 2019/3/23.
 */
@SpringBootApplication
public class WechatWebApplication {
  public static void main(String[] args) {
//    InitExecutor.doInit();
    //setRocketMQConfig();
    SpringApplicationBuilder builder = new SpringApplicationBuilder();
    builder.sources(WechatWebApplication.class);
    builder.run(args);
  }
}