package com.wechat.web.configuration;

import com.cootf.wechat.config.WXPayConfig;
import com.qq.weixin.mp.wxpay.IWXPayDomain;
import com.qq.weixin.mp.wxpay.WXPayDomainSimpleImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * Created by mjg on 2019/3/26.
 */
@Configuration
public class WeChatConfiguration extends WXPayConfig {

  @Value("${wxPay.notifyUrl}")
  private String notifyUrl;//微信支付订单回调地址.

  @Value("${wechat.appid}")
  private String appId;

  @Value("${wechat.secret}")
  private String secret;

  @Value("${wechat.redirectUrl}")
  private String redirectUri;

  @Value("${wxPay.mchId}")
  private String mchId;//商户号ID.
  @Value("${wxPay.key}")
  private String key;//密钥.

  @Value("${wxPay.httpConnectTimeOut}")
  private int httpConnectTimeoutMs;//HTTP(S) 连接超时时间，单位毫秒.
  @Value("${wxPay.httpReadTimeOut}")
  private int httpReadTimeoutMs;//HTTP(S) 读数据超时时间，单位毫秒.
  @Value("${wxPay.shouldAutoReport}")
  private boolean shouldAutoReport;//是否自动上报。 若要关闭自动上报，子类中实现该函数返回 false 即可。.
  @Value("${wxPay.reportWorkerNum}")
  private int reportWorkerNum;//进行健康上报的线程的数量.
  @Value("${wxPay.reportQueueMaxSize}")
  private int reportQueueMaxSize;//健康上报缓存消息的最大数量。会有线程去独立上报 粗略计算：加入一条消息200B，10000消息占用空间 2000 KB，约为2MB，可以接受.
  @Value("${wxPay.reportBatchSize}")
  private int reportBatchSize;//批量上报，一次最多上报多个数据.
  @Value("${wxPay.autoReport}")
  private boolean autoReport;
  @Value("${wxPay.useSandbox}")
  private boolean useSandbox;

  public String getSecret() {
    return secret;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  @Override
  public boolean isAutoReport() {
    return autoReport;
  }

  @Override
  public boolean isUseSandbox() {
    return useSandbox;
  }

  @Override
  public String getNotifyUrl() {
    return notifyUrl;
  }

  @Override
  public String getAppID() {
    return appId;
  }

  @Override
  public String getMchID() {
    return mchId;
  }

  public String getKey() {
    return key;
  }

  @Override
  public InputStream getCertStream() {
    return null;
  }

  @Override
  public IWXPayDomain getWXPayDomain() {
    return WXPayDomainSimpleImpl.instance();
  }
}