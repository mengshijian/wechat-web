package com.wechat.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Auther: win7
 * @Date: 2019/4/8 10:39
 * @Description:微信用户
 */
public class WechatCustom extends Custom {

  private static final long serialVersionUID = -245054009515138515L;
  //昵称
  private String nickName;
  //国家
  private String country;
  //省份
  private String province;
  //城市
  private String city;
  //头像地址
  private String headImgUrl;

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getHeadImgUrl() {
    return headImgUrl;
  }

  public void setHeadImgUrl(String headImgUrl) {
    this.headImgUrl = headImgUrl;
  }

  @Override
  public String toString() {
    return "WechatCustom{" +
      "nickName=" + nickName +
      ", country='" + country + '\'' +
      ", city='" + city + '\'' +
      ", headImgUrl='" + headImgUrl + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
