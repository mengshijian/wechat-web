package com.wechat.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wechat.web.consts.Constant;
import com.wechat.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName: CustomChekInterceptor
 *
 * @author mengsj
 * @Description: 用户授权拦截器
 * @date 2019年04月08日
 *
 * =================================================================================================
 * Task ID			  Date			     Author		      Description ----------------+----------------+-------------------+-------------------------------------------
 */
@Configuration
public class TokenCheckInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(TokenCheckInterceptor.class);

  @Override
  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
    throws Exception {
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView model)
    throws Exception {
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
    // 1. 用于过滤区分 mvc:resources
    if ((handler == null) || (!HandlerMethod.class.isAssignableFrom(handler.getClass()))) {
      return true;
    }
    String loginUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

    String token = request.getHeader(Constant.AUTH);
    String reqUrl = request.getRequestURI();

    String ua = request.getHeader("user-agent").toLowerCase();

    if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
      if (reqUrl.indexOf("wechat") > 0 && StringUtils.isBlank(token)){
        return true;
      } else {
        Boolean flag = checkToken(token,Constant.OPEN_ID);
        if (!flag){
          response.setStatus(Constant.AUTH_ERROR_CODE);
        }
        return flag;
      }
    } else {
      Boolean flag = checkToken(token,Constant.CUSTOM_ID);
      if (!flag){
        response.setStatus(Constant.AUTH_ERROR_CODE);
      }
      return flag;
    }
  }

  private boolean checkToken(String token, String checkFiled) {
    if (StringUtils.isBlank(token)) {
      return false;
    }
    Claims claims = JwtUtils.parseJWT(token);
    if (claims == null || !claims.getIssuer().equalsIgnoreCase(Constant.JWT_ISSUER)) {
      return false;
    }
    JSONObject json = (JSONObject) JSONObject.parse(claims.getSubject());
    String field = json.get(checkFiled).toString();
    //如果用户信息存在，则放行
    if (StringUtils.isBlank(field)) {
      return false;
    }
    return true;
  }
}