package com.wechat.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.web.annotation.JsonRequest;
import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

/**
 * @Auther: win7
 * @Date: 2019/4/11 10:35
 * @Description:
 */
@Component
public class ParameterResolverHandler implements HandlerMethodArgumentResolver  {

  //存储json
  private static ThreadLocal<JSONObject> threadLocal = new ThreadLocal<>();

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.hasParameterAnnotation(JsonRequest.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
      ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
    // content-type不是json的不处理
    if (!request.getContentType().contains("application/json")) {
      return null;
    }
    Object arg = null;
    // 把reqeust的body读取到StringBuilder
    JSONObject json = threadLocal.get();
    if (json == null){
      BufferedReader reader = request.getReader();
      StringBuilder sb = new StringBuilder();

      char[] buf = new char[1024];
      int rd;
      while((rd = reader.read(buf)) != -1){
        sb.append(buf, 0, rd);
      }
      threadLocal.set(JSONObject.parseObject(sb.toString()));
    }
    json = threadLocal.get();
    // 获取Controller中的参数名
    String param = parameter.getParameterName();
    //获取注解
    JsonRequest jsonRequest = parameter.getParameterAnnotation(JsonRequest.class);
    if (StringUtils.isNotBlank(jsonRequest.value())){
      arg = json.get(jsonRequest.value());
    } else {
      arg = json.get(param);
    }
    return arg;
  }
}
