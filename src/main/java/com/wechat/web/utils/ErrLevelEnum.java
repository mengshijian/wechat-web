package com.wechat.web.utils;

/**
 * Created by mjg on 2018/8/1.
 */
public enum ErrLevelEnum {

  FATAL(0, "最严重FATAL"),
  ERROR(1, "错误ERROR"),
  WARNING(2, "警告WARNING"),
  INFO(3, "信息INFO"),
  TRACE(4, "跟踪TRACE"),
  DEBUG(5, "调试DEBUG");

  private int code;
  private String desc;

  ErrLevelEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static String getDesc(int code) {
    for (ErrLevelEnum businessType : ErrLevelEnum.values()) {
      if (businessType.getCode() == code) {
        return businessType.getDesc();
      }
    }
    return "";
  }

  public static ErrLevelEnum getEnum(int code) {
    for (ErrLevelEnum businessType : ErrLevelEnum.values()) {
      if (businessType.getCode() == code) {
        return businessType;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}