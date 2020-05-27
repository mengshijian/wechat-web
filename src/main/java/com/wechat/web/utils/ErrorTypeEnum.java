package com.wechat.web.utils;

/**
 * Created by mjg on 2018/8/1.
 */
public enum ErrorTypeEnum {

  ERROR_NONE(0x00, "ERROR_NONE"),
  ERROR_EXISTED(0x22, "ERROR_EXISTED"),
  ERROR_EMPTY(0x23, "ERROR_NOT_EXISTED"),
  ERROR_PARAM(0x24, "ERROR_PARAM"),
  ERROR_SERVER(0x25, "ERROR_SERVER"),
  INFO_USER(0x26, "INFO_USER"),
  INFO_SIMCARD(0x27, "INFO_SIMCARD"),
  INFO_SIMPOOL(0x28, "INFO_SIMPOOL");

  private int code;
  private String desc;

  ErrorTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static String getDesc(int code) {
    for (ErrorTypeEnum businessType : ErrorTypeEnum.values()) {
      if (businessType.getCode() == code) {
        return businessType.getDesc();
      }
    }
    return "";
  }

  public static ErrorTypeEnum getEnum(int code) {
    for (ErrorTypeEnum businessType : ErrorTypeEnum.values()) {
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