package com.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.web.consts.Constant;
import com.wechat.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mjg on 2017/6/19.
 */
public class BaseController {

  /**
   * TOKEN解析
   * @param request
   * @author mengsj
   * @return jsonObject
   */
  public JSONObject convertToken(HttpServletRequest request){
    String token = request.getHeader(Constant.AUTH);
    if (StringUtils.isBlank(token)) return new JSONObject();
    Claims claims = JwtUtils.parseJWT(token);
    JSONObject json = (JSONObject) JSONObject.parse(claims.getSubject());
    return json;
  }
}
