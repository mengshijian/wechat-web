
package com.wechat.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.cootf.wechat.bean.message.EventMessage;
import com.cootf.wechat.bean.xmlmessage.XMLMessage;
import com.cootf.wechat.bean.xmlmessage.XMLTextMessage;
import com.cootf.wechat.util.XMLConverUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WxChatEventListerController {

  private static final Logger logger = LoggerFactory.getLogger(WxChatEventListerController.class);

  @Value("${wechat.appid}")
  private String wxAppid;

  @Value("${wechat.token}")
  private String token;

  @Value("${wechat.encodingAESKey}")
  private String encodingAESKey;

  @RequestMapping(value = "/wechat/validate", method = RequestMethod.POST)
  @ResponseBody
  public String recMessage(HttpServletRequest request) {
    try {
      ServletInputStream inputStream = request.getInputStream();

      if (inputStream != null) {
        //转换XML
        EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
        String key = eventMessage.getFromUserName() + "__"
          + eventMessage.getToUserName() + "__"
          + eventMessage.getMsgId() + "__"
          + eventMessage.getCreateTime();

        logger.info("消息体:{}", JSONObject.toJSONString(eventMessage));
        //创建回复
        XMLMessage xmlTextMessage = new XMLTextMessage(
          eventMessage.getFromUserName(),
          eventMessage.getToUserName(),
          "你好");
        //回复
        return xmlTextMessage.toXML();
      }
      return "";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  @RequestMapping(value = "/wechat/validate", method = RequestMethod.GET)
  @ResponseBody
  public String checkUrl(String signature, String timestamp, String nonce, String echostr) {
    Boolean flag = validateSign(signature, timestamp, nonce, echostr);
    if (flag) {
      return echostr;
    }
    return "sss";
  }

  private Boolean validateSign(String signature, String timestamp, String nonce, String echostr) {
    if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)
      || StringUtils.isEmpty(echostr)) {
      return false;
    }
    String jiami = "";
    try {
      jiami = SHA1.getSHA1(token, timestamp, nonce, "");//这里是对三个参数进行加密
    } catch (AesException e) {
      logger.error("签名验证失败{}", e);
    }
    System.out.println("加密" + jiami);
    System.out.println("本身" + signature);
    if (jiami.equals(signature)) {
      return true;
    } else {
      return false;
    }
  }
}
