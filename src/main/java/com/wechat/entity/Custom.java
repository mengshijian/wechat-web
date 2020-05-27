package com.wechat.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 功能：MIFI微信公众号用户对象. Created by mjg on 2019/3/22.
 */
public class Custom implements Serializable {

  private static final long serialVersionUID = 3828754761735466664L;
  private Integer id;
  private String customId;  //用户ID.
  private String userName;  //用户名.
  private String loginPwd;  //登录密码.
  private String phoneNumber; //手机号码.
  private Integer isCertified;//是否已实名认证(0-否；1-是).
  private String openId;  //微信授权唯一标示.
  private Long createTime;//记录生成时间.
  private Long lastUpdateTime;//最后修改时间.
  private Integer status;//状态(0-正常；1-禁用).
  private String nickName;//公众号用户微信昵称.

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCustomId() {
    return customId;
  }

  public void setCustomId(String customId) {
    this.customId = customId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLoginPwd() {
    return loginPwd;
  }

  public void setLoginPwd(String loginPwd) {
    this.loginPwd = loginPwd;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Integer getIsCertified() {
    return isCertified;
  }

  public void setIsCertified(Integer isCertified) {
    this.isCertified = isCertified;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public Long getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Long lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  @Override
  public String toString() {
    return "Custom{" +
      "id=" + id +
      ", customId='" + customId + '\'' +
      ", userName='" + userName + '\'' +
      ", loginPwd='" + loginPwd + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", isCertified=" + isCertified +
      ", openId='" + openId + '\'' +
      ", createTime=" + createTime +
      ", lastUpdateTime=" + lastUpdateTime +
      ", status=" + status +
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