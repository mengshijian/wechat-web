package com.wechat.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mjg on 2019/3/22.
 */
public enum BooleanEnum {

  FALSE(0, "否"),
  TRUE(1, "是");

  private int code;
  private String desc;
  private static Map<String, String> statusMap = new HashMap<>();

  public static Map<String, String> getStatusMap() {
    return statusMap;
  }

  static {
    BooleanEnum[] var0 = values();
    int var1 = var0.length;

    for (int var2 = 0; var2 < var1; ++var2) {
      BooleanEnum statusEnum = var0[var2];
      statusMap.put(statusEnum.getCode() + "", statusEnum.getDesc());
    }
  }

  BooleanEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static String getDesc(int code) {
    for (BooleanEnum failReasonEnum : BooleanEnum.values()) {
      if (failReasonEnum.getCode() == code) {
        return failReasonEnum.getDesc();
      }
    }
    return "";
  }

  public static BooleanEnum getEnum(int code) {
    for (BooleanEnum failReasonEnum : BooleanEnum.values()) {
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