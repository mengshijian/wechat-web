package com.wechat.web.listener;

import com.cootf.wechat.support.TicketManager;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TicketManagerListener implements ServletContextListener {

  @Value("${wechat.appid}")
  private String wxAppid;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    //WEB容器 初始化时调用
    //2.6.1 版本新增，延迟5秒执行。
    TicketManager.init(wxAppid, 5, 60 * 119);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //WEB容器  关闭时调用
    TicketManager.destroyed();
  }
}
