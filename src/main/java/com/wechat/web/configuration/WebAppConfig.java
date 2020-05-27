package com.wechat.web.configuration;

import com.wechat.web.interceptor.TokenCheckInterceptor;
import com.wechat.web.listener.TicketManagerListener;
import com.wechat.web.listener.TokenManagerListener;
import com.wechat.web.listener.WechatListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: win7
 * @Date: 2019/4/11 10:38
 * @Description:拦截器配置
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

  @Autowired
  private TokenCheckInterceptor customCheckInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(customCheckInterceptor).addPathPatterns("/**")
        .excludePathPatterns(
            "/wechat/validate",
            "/order/wxNotify",
            "/error",
            "/login",
            "/validateCode/**",
            "/js/config",
            "/**.html",
            "/getHostCtx"); // 对来自/user/** 这个链接来的请求进行拦截
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedMethods("*")
      .allowedOrigins("*")
      .allowedHeaders("authorization", "Accept");
  }

  @Bean
  public TokenManagerListener tokenManagerListener() {
    return new TokenManagerListener();
  }

  @Bean
  public TicketManagerListener ticketManagerListener() {
    return new TicketManagerListener();
  }

  @Bean
  public WechatListener weChatListener() {
    return new WechatListener();
  }
}
