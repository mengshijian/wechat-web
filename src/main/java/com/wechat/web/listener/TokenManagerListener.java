package com.wechat.web.listener;

import com.cootf.wechat.support.TokenManager;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TokenManagerListener implements ServletContextListener {

  @Value("${wechat.appid}")
  private String wxAppid;

  @Value("${wechat.secret}")
  private String secret;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    //WEB容器 初始化时调用
    TokenManager.init(wxAppid, secret);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //WEB容器  关闭时调用
    TokenManager.destroyed();
  }
}
