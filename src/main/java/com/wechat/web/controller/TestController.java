package com.wechat.web.controller;

import com.wechat.web.configuration.WeChatConfiguration;
import com.cootf.wechat.api.MessageAPI;
import com.cootf.wechat.bean.message.templatemessage.TemplateMessage;
import com.cootf.wechat.support.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Auther: win7
 * @Date: 2019/4/29 11:19
 * @Description:
 */
@Controller
public class TestController {

  @Autowired
  private WeChatConfiguration weChatConfig;

  @GetMapping(value = "/wechat/sendMsg")
  public void sendTemplateMsg(){
    TemplateMessage tmsg = new TemplateMessage();
    tmsg.setTouser("oZDja1b205_LiTafH9RWq--fkL7M");
    tmsg.setTemplate_id("_36C13BFrBzDlwlUmdXCjSP9F4AKJyTMgO5PgMY08Tw");
    MessageAPI.messageTemplateSend(TokenManager.getToken(weChatConfig.getAppID()),tmsg);
  }
}
