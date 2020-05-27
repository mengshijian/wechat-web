package com.wechat.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mjg on 2018/4/24.
 */
public enum UserStatusEnum {

  NORMAL(0, "正常"),
  LOCKED(1, "已锁定");

  private int code;
  private String desc;
  private static Map<String, String> statusMap = new HashMap<>();

  public static Map<String, String> getStatusMap() {
    return statusMap;
  }

  static {
    UserStatusEnum[] var0 = values();
    int var1 = var0.length;

    for (int var2 = 0; var2 < var1; ++var2) {
      UserStatusEnum statusEnum = var0[var2];
      statusMap.put(statusEnum.getCode() + "", statusEnum.getDesc());
    }
  }

  UserStatusEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static String getDesc(int code) {
    for (UserStatusEnum failReasonEnum : UserStatusEnum.values()) {
      if (failReasonEnum.getCode() == code) {
        return failReasonEnum.getDesc();
      }
    }
    return "";
  }

  public static UserStatusEnum getEnum(int code) {
    for (UserStatusEnum failReasonEnum : UserStatusEnum.values()) {
      if (failReasonEnum.getCode() == code) {
        return failReasonEnum;
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
