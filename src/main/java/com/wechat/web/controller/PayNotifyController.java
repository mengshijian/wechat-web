package com.wechat.web.controller;

import com.cootf.wechat.api.MessageAPI;
import com.cootf.wechat.bean.message.templatemessage.TemplateMessage;
import com.cootf.wechat.bean.paymch.MchPayNotify;
import com.cootf.wechat.bean.paymch.RefundNotifyReqInfo;
import com.cootf.wechat.support.TokenManager;
import com.cootf.wechat.support.WeChatNotifyProcessor;
import com.cootf.wechat.util.PayUtil;
import com.cootf.wechat.util.StreamUtils;
import com.cootf.wechat.util.XMLConverUtil;
import com.wechat.web.configuration.WeChatConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 功能：支付回调控制器. Created by mjg on 2019/3/26.
 */
@RestController
public class PayNotifyController extends BaseController {

  private static final Logger logger = LoggerFactory.getLogger(PayNotifyController.class);
  @Autowired
  private WeChatConfiguration weChatConfiguration;

  @Value("${pay.notify.template}")
  private String payTemplateId;

  @Value("${refund.notify.template}")
  private String refundTemplateId;

  /**
   * 功能：微信支付回调接口.
   */
  @PostMapping("/order/wxNotify")
  public Object wxOrderNotify(HttpServletRequest request) {
    try {
      String xmlData = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
      return new UserWeChatNotifyProcessor().processNotify(xmlData);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  private TemplateMessage initMessage(MchPayNotify notify){
    TemplateMessage message = new TemplateMessage();
    message.setTouser(notify.getOpenid());
    message.setTemplate_id(payTemplateId);
    return message;
  }

  class UserWeChatNotifyProcessor extends WeChatNotifyProcessor {

    @Override
    public String processNotify(String s) {
      Map<String, String> mapData = XMLConverUtil.convertToMap(s);
      if (mapData.containsKey("req_info")) {
        //退款单
        return PayUtil.processRefundResult(s, weChatConfiguration.getKey(), this);
      } else {
        //支付结果通知
        return PayUtil.processPayResult(s, weChatConfiguration.getKey(), this);
      }
    }

    @Override
    public boolean payResultProcess(MchPayNotify mchPayNotify) {
      //发送模板消息
      TemplateMessage message = initMessage(mchPayNotify);
      MessageAPI.messageTemplateSend(TokenManager.getToken(weChatConfiguration.getAppID()),message);
      return true;
    }

    @Override
    public boolean refundResultProcess(RefundNotifyReqInfo refundNotify) {
      return true;
    }
  }
}