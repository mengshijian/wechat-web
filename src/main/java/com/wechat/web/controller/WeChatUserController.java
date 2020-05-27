package com.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.cootf.wechat.api.SnsAPI;
import com.cootf.wechat.bean.sns.SnsToken;
import com.cootf.wechat.bean.user.User;
import com.wechat.entity.WechatCustom;
import com.wechat.service.WechatCustomService;
import com.wechat.web.configuration.LocaleMessageSource;
import com.wechat.web.configuration.WeChatConfiguration;
import com.wechat.web.consts.Constant;
import com.wechat.web.utils.CodeGeneraterUtil;
import com.wechat.web.utils.DateTimeUtil;
import com.wechat.web.utils.JwtUtils;
import com.wechat.web.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mengsj on 2019/3/22. 微信登录相关接口
 */
@Controller
public class WeChatUserController extends BaseController {

  private static Logger logger = LoggerFactory.getLogger(WeChatUserController.class);

  @Autowired
  private WeChatConfiguration weChatConfig;

  @Autowired
  private WechatCustomService wechatCustomService;

  @Resource
  private LocaleMessageSource localeMessageSource;

  @Value("${smifi.web.page.root.url}")
  private String webRootUrl;

  /**
   * 微信授权
   */
  @GetMapping(value = "/wechat/authwx")
  public String authWx(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String state = RandomUtil.getVerifyCodeString(4);
    String url = SnsAPI.connectOauth2Authorize(weChatConfig.getAppID(),weChatConfig.getRedirectUri(),true,state);
    // 随机数，获取openid时验证是否一样
    session.setAttribute("state", state);
    return "redirect:" + url;
  }
  /**
   * 进入微信初始页
   *
   * @mengsj 2019-04-08 start
   */
  @GetMapping(value = "/wechat/index")
  public String findIndex(HttpServletRequest request,HttpServletResponse response) {
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    HttpSession session = request.getSession();
    session.setAttribute(Constant.BACK_URL,request.getParameter(Constant.BACK_URL));
    if (state != null && session.getAttribute("state").equals(state) && StringUtils
        .isNotBlank(code)) {
      logger.info("code:{}",code );
      Object temp = session.getAttribute("accessToken");
      String assToken = temp != null ? temp.toString() : null;
      if (StringUtils.isBlank(assToken) && StringUtils.isNotBlank(code)){
        //获取openid
        SnsToken token = SnsAPI.oauth2AccessToken(weChatConfig.getAppID(), weChatConfig.getSecret(), code);
        logger.info("微信token返回值====>{}", JSONObject.toJSONString(token));
        String openId = token.getOpenid();
        assToken = token.getAccess_token();
        //防止http请求操作重试，
        session.setAttribute("accessToken", assToken);
        if (StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(assToken)){
          //获取用户信息
          User user = SnsAPI.userinfo(assToken, openId, "zh_CN", 0);
          logger.info("微信用户信息====>{}", JSONObject.toJSONString(user));
          WechatCustom wechatCustom = saveOrUpdateCustom(user);
          JSONObject json = new JSONObject();
          json.put(Constant.OPEN_ID, openId);
          json.put(Constant.CUSTOM_ID, wechatCustom.getCustomId());
          String auth = JwtUtils.createJWT(JSONObject.toJSONString(json), Constant.AUTH_EX_TIME);
          // 将AUTH添加到Response中,使之生效
          response.setHeader(Constant.TOKEN, auth);
        }
      }
      String backUrl =
          session.getAttribute(Constant.BACK_URL) != null ? session.getAttribute(Constant.BACK_URL)
              .toString() : webRootUrl;
      session.removeAttribute(Constant.BACK_URL);
      return "redirect:"+ backUrl +"?" + Constant.TOKEN + "=" + response.getHeader(Constant.TOKEN);
    }
    return "forward:/wechat/authwx";
  }

  private WechatCustom saveOrUpdateCustom(User user) {
    String openId = user.getOpenid();
    WechatCustom custom = null;
    if (StringUtils.isNotBlank(openId)) {
        custom = wechatCustomService.getByOpenId(openId).getData();
      //新增微信用户
      if (custom == null) {
        custom = initCustom(user);
        wechatCustomService.insert(custom);
      } else {
        //修改微信用户
        custom.setCountry(user.getCountry());
        custom.setProvince(user.getProvince());
        custom.setCity(user.getCity());
        custom.setNickName(user.getNickname());
        custom.setHeadImgUrl(user.getHeadimgurl());
        custom.setLastUpdateTime(DateTimeUtil.getUTCTimeStamp());
        wechatCustomService.updateByOpenId(custom);
      }
    }
    return custom;
  }

  private WechatCustom initCustom(User user) {
    WechatCustom custom = new WechatCustom();
    custom.setCustomId(CodeGeneraterUtil.generateCustomId());
    custom.setOpenId(user.getOpenid());
    custom.setCreateTime(DateTimeUtil.getUTCTimeStamp());
    custom.setLastUpdateTime(DateTimeUtil.getUTCTimeStamp());
    custom.setCountry(user.getCountry());
    custom.setProvince(user.getProvince());
    custom.setCity(user.getCity());
    custom.setNickName(user.getNickname());
    custom.setHeadImgUrl(user.getHeadimgurl());
    return custom;
  }
}