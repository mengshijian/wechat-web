package com.wechat.web.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.SecureRandom;

public class StringUtil {

  /**
   * 格式化模糊查询
   */
  public static String formatLike(String str) {
    if (StringUtils.isNotBlank(str)) {
      return "%" + str.trim() + "%";
    } else {
      return null;
    }
  }

  /**
   * 功能：获取指定机器的mac地址(如果未提供指定机器，则获取本机).
   */
  public static String getLocalMac(InetAddress ia) {
    //如果未提供ip地址，则获取当前本机的ip.
    if (ia == null) {
      try {
        ia = InetAddress.getLocalHost();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
    }
    //获取网卡，获取地址
    byte[] mac = new byte[0];
    try {
      if (ia != null) {
        mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < mac.length; i++) {
      //字节转换为整数
      int temp = mac[i] & 0xff;
      String str = Integer.toHexString(temp);
      if (str.length() == 1) {
        sb.append("0" + str);
      } else {
        sb.append(str);
      }
      if (i < mac.length - 1) {
        sb.append(":");
      }
    }
    return sb.toString().toLowerCase();
  }

  /*
   * 返回长度为【strLength】的随机数，在前面补0
   */
  public static String getFixLenthRandom(int strLength) {
    SecureRandom rm = new SecureRandom();
    // 获得随机数
    double pross = (1 + rm.nextDouble() * 9) * Math.pow(10, strLength);

    // 将获得的获得随机数转化为字符串
    String fixLenthString = String.valueOf(pross);

    // 返回固定的长度的随机数
    return fixLenthString.substring(1, strLength + 1);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(getFixLenthRandom(6));
    }
  }
}
