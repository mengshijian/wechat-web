package com.wechat.web.utils;

import java.util.Random;

/**
 * @Auther: win7
 * @Date: 2019/4/6 15:58
 * @Description:随机数
 */
public class RandomUtil {

  private static String[] seedArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
    "W", "X", "Y", "Z"};

  /**
   * DESC: 生成验证码
   *
   * @param length 验证码位数
   * @author LIU.W
   */
  public static String getVerifyCodeString(int length) {
    Random random = new Random();
    StringBuffer verifyCode = new StringBuffer();
    //循环获取验证码字符
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(seedArray.length);
      verifyCode.append(seedArray[index]);
    }
    return verifyCode.toString();
  }

}
