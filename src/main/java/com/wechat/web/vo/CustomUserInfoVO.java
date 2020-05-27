package com.wechat.web.vo;

/**
 * Created by mjg on 2019/4/5.
 */
public class CustomUserInfoVO {

  private String customId;
  private String phoneNumber;
  private String realName;
  private Boolean isCertified;
  //昵称
  private String nickName; //微信昵称

  //头像地址
  private String headImgUrl; //微信头像

  public String getCustomId() {
    return customId;
  }

  public void setCustomId(String customId) {
    this.customId = customId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public Boolean getCertified() {
    return isCertified;
  }

  public void setCertified(Boolean certified) {
    isCertified = certified;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getHeadImgUrl() {
    return headImgUrl;
  }

  public void setHeadImgUrl(String headImgUrl) {
    this.headImgUrl = headImgUrl;
  }
}