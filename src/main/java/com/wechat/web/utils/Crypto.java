package com.wechat.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author:ice
 * @Date: 2018/6/6 11:52
 */
public class Crypto {

  /**
   * MD5加密
   */
  public static final String MD5_16bit(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      try {
        md.update(input.getBytes("UTF-8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
//            return md.digest();
      byte resultByteArray[] = md.digest();
      StringBuffer strBuf = new StringBuffer();
      int b;
      for (int i = 0; i < resultByteArray.length; i++) {
        b = 0xFF & resultByteArray[i];
        strBuf.append(Integer.toHexString((b >>> 4) & 0xF));
        strBuf.append(Integer.toHexString(b & 0xF));
      }
      return strBuf.toString();
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
  }
}
