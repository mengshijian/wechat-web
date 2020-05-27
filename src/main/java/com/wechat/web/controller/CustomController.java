package com.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.wechat.entity.Custom;
import com.wechat.entity.WechatCustom;
import com.wechat.service.CustomService;
import com.wechat.service.WechatCustomService;
import com.wechat.web.configuration.LocaleMessageSource;
import com.wechat.web.consts.Constant;
import com.wechat.web.utils.BooleanEnum;
import com.wechat.web.utils.JwtUtils;
import com.wechat.web.utils.RpcFailReasonEnum;
import com.wechat.web.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：手机号码登录控制器. Created by mjg on 2019/3/22.
 */
@RestController
public class CustomController extends BaseController {

  @Autowired
  private CustomService customService;
  @Autowired
  private WechatCustomService wechatCustomService;
  @Resource
  private LocaleMessageSource localeMessageSource;

  /**
   * 功能：手机号码登录微信公众号.
   */
  @PostMapping(value = "/login")
  public Rsp phoneLogin(@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
                        @RequestParam(value = "password", required = true) String loginPwd,
                        HttpServletResponse response) {
    PhoneLoginVO phoneLoginVO = new PhoneLoginVO();
    RpcResult<String> userRpcResult = customService.checkLoginKey(phoneNumber, loginPwd);
    RpcFailReasonEnum result = RpcFailReasonEnum.getEnum(userRpcResult.getCode());
    String customId = null;
    if (userRpcResult instanceof RpcSuccessResult && StringUtils
      .isNotBlank(userRpcResult.getData())) {
      customId = userRpcResult.getData();
    }
    phoneLoginVO.setCustomId(customId);
    JSONObject json = new JSONObject();
    json.put(Constant.CUSTOM_ID, customId);
    String auth = JwtUtils.createJWT(JSONObject.toJSONString(json), Constant.AUTH_EX_TIME);
    // 将AUTH添加到Response中,使之生效
    response.setHeader(Constant.TOKEN, auth);
    return Rsp
      .transEnd(result.getCode(), localeMessageSource.getRpcMessage(result), phoneLoginVO);
  }

  /**
   * 功能：获取指定用户信息.
   */
  @PostMapping(value = "/user/{customId}")
  public Rsp getCustomInfo(@PathVariable(value = "customId", required = true) String customId) {
    CustomPageVO customPageVO = new CustomPageVO();

    RpcResult<Custom> customRpcResult = customService.getCustomByCustomId(customId);
    RpcFailReasonEnum result = RpcFailReasonEnum.getEnum(customRpcResult.getCode());
    if (customRpcResult.isSuccess() && customRpcResult.getData() != null) {
      CustomUserInfoVO userInfoVO = new CustomUserInfoVO();
      Custom custom = customRpcResult.getData();
      userInfoVO.setCustomId(custom.getCustomId());
      userInfoVO.setPhoneNumber(custom.getPhoneNumber());
      userInfoVO.setRealName(custom.getUserName());
      userInfoVO.setCertified(custom.getIsCertified() == BooleanEnum.TRUE.getCode());
      if (StringUtils.isNotBlank(custom.getOpenId())){
        WechatCustom wechatCustom = wechatCustomService.getByOpenId(custom.getOpenId()).getData();
        if (wechatCustom != null){
          userInfoVO.setNickName(wechatCustom.getNickName());
          userInfoVO.setHeadImgUrl(wechatCustom.getHeadImgUrl());
        }
      }
      customPageVO.setUser(userInfoVO);
    }
    return Rsp
      .transEnd(result.getCode(), localeMessageSource.getRpcMessage(result), customPageVO);
  }
}