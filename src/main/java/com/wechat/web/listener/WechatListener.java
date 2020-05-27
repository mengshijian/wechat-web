package com.wechat.web.listener;

import com.cootf.wechat.api.MenuAPI;
import com.cootf.wechat.bean.BaseResult;
import com.cootf.wechat.support.TicketManager;
import com.cootf.wechat.support.TokenManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class WechatListener implements ApplicationListener<ContextRefreshedEvent> {

  @Value("${wechat.appid}")
  private String wxAppid;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    String menuJso = "{\"button\":[{\"name\": \"类型\",\"sub_button\":[{\"type\": \"view\",\"name\":\"首页\",\"url\": \"http://smifi.3w.dkys.org/mifi/index.html\"},{\"type\": \"scancode_push\",\"name\":\"扫一扫\",\"key\":\"11\"},{\"type\": \"pic_sysphoto\",\"name\":\"拍照发图\",\"key\":\"22\"},{\"type\": \"pic_weixin\",\"name\":\"相册\",\"key\":\"33\"},{\"type\": \"location_select\",\"name\":\"位置分享\",\"key\":\"44\"}]},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://mifi.3w.dkys.org/wechat/sendMsg\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
    String access_token = TokenManager.getToken(wxAppid);
    BaseResult result = MenuAPI.menuCreate(access_token,menuJso);
    System.out.println(result);

  }
}
