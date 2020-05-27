package com.wechat.web.consts;

public final class Constant {

  public final static String OPEN_ID = "open_id";
  public final static String CUSTOM_ID = "custom_id";
  public final static String AUTH = "Authorization";
  public final static String TOKEN = "Token";
  public final static String BACK_URL = "backUrl";

  /**
   * jwt使用的签名字符串
   */
  public static final String BASE64_SECURITY = "ctf2018";

  public static final String JWT_ISSUER = "ctf";

  /**
   * 2小时
   */
  public static final long AUTH_EX_TIME = 3600000L * 2;

  public static final Integer AUTH_ERROR_CODE = 401;
}
