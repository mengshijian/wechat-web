package com.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.cootf.wechat.support.TicketManager;
import com.cootf.wechat.util.JsUtil;
import com.wechat.web.configuration.LocaleMessageSource;
import com.wechat.web.utils.RpcFailReasonEnum;
import com.wechat.web.vo.Rsp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Auther: win7
 * @Date: 2019/4/16 15:46
 * @Description:微信js相关接口
 */
@RestController
public class WechatJsController {

  @Value("${wechat.appid}")
  private String wxAppid;

  @Resource
  private LocaleMessageSource localeMessageSource;

  private static Logger logger = LoggerFactory.getLogger(WechatJsController.class);

  @PostMapping(value = "/js/config")
  public Rsp createConfig(String url) {
    RpcFailReasonEnum result = RpcFailReasonEnum.SUCCEED;
    try {
      String ticket = TicketManager.getTicket(wxAppid);
      String json = JsUtil
          .generateConfigJson(ticket, true, wxAppid, URLDecoder.decode(url, "utf-8"));
      return Rsp.transEnd(result.getCode(), localeMessageSource.getRpcMessage(result),
          JSONObject.parseObject(json));
    } catch (UnsupportedEncodingException e) {
      logger.error("生成jsapi配置失败:{}",e.getStackTrace());
    }
    return Rsp.transEnd(result.getCode(), localeMessageSource.getRpcMessage(result), null);
  }
}
