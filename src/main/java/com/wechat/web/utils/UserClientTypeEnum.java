package com.wechat.web.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mjg on 2018/4/27.
 */
public enum UserClientTypeEnum {

  MC_WILL(1, "M网用户"),
  RESIM(2, "App注册用户"),
  MIFI(3, "MIFI用户"),
  MP_User(4, "猫池用户");

  private int code;
  private String desc;
  private static Map<String, String> statusMap = new HashMap<>();

  public static Map<String, String> getStatusMap() {
    return statusMap;
  }

  static {
    UserClientTypeEnum[] var0 = values();
    int var1 = var0.length;

    for (int var2 = 0; var2 < var1; ++var2) {
      UserClientTypeEnum statusEnum = var0[var2];
      statusMap.put(statusEnum.getCode() + "", statusEnum.getDesc());
    }
  }

  UserClientTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static String getDesc(int code) {
    for (UserClientTypeEnum statusEnum : UserClientTypeEnum.values()) {
      if (statusEnum.getCode() == code) {
        return statusEnum.getDesc();
      }
    }
    return "";
  }

  public static UserClientTypeEnum getEnum(int code) {
    for (UserClientTypeEnum statusEnum : UserClientTypeEnum.values()) {
      if (statusEnum.getCode() == code) {
        return statusEnum;
      }
    }
    return null;
  }

  public static UserClientTypeEnum getEnumByDesc(String desc) {
    for (UserClientTypeEnum statusEnum : UserClientTypeEnum.values()) {
      if (StringUtils.equalsIgnoreCase(desc, statusEnum.getDesc())) {
        return statusEnum;
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
