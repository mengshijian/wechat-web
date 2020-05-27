package com.wechat.web.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：国际化配置拦截器. Created by mjg on 2018/1/6.
 */
@Configuration
public class I18nInterceptor extends LocaleChangeInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler) {
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    Cookie[] cookies = request.getCookies();

    String lang = request.getHeader("lang");

    if (StringUtils.isBlank(lang)) {
      lang = request.getParameter("locale");
    }
    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        String cookieName = cookies[i].getName();
        if (cookieName.equals("locale")) {
          lang = cookies[i].getValue();
        }
      }
    }
    if (localeResolver != null) {
      if (StringUtils.isNotBlank(lang)) {
        localeResolver.setLocale(request, response, parseLocaleValue(lang.toLowerCase()));
      } else {
        //先从cookie中获取，cookie中没有再从header中获取 language:en,zh_CN
        localeResolver.setLocale(request, response, localeResolver.resolveLocale(request));
      }
    }
    return true;
  }
}
