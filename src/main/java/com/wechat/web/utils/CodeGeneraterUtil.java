package com.wechat.web.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * 功能：编码生成工具类. Created by mjg on 2018/4/27.
 */
public class CodeGeneraterUtil {


  /**
   * 功能：生成订单号.
   */
  public static String generateOrderNo(String topCode) {
    String timeStamp = DateTimeUtil.getMiSecondStr();
    int random = generaterSixNum();
    StringBuffer buffer = new StringBuffer();
    buffer.append(topCode);
    buffer.append(timeStamp);
//    //当前机器的mac地址.
//    buffer.append(StringUtil.getMacAddress());
    buffer.append(random);
    return buffer.toString();
  }

  /**
   * 功能：生成终端用户ID.
   *
   * @param type 用户类别.
   */
  public static String generateClientId(String topCode, String mcc, UserClientTypeEnum type) {
    String timeStamp = DateTimeUtil.getMiSecondStr();
    int random = generaterSixNum();
    StringBuffer buffer = new StringBuffer();
    buffer.append(topCode);
    buffer.append(mcc);
    buffer.append(timeStamp);
    buffer.append(random);
    return buffer.toString();
  }

  /**
   * 功能：生成客户编码.
   */
  public static String generateOrgCode(String topCode, int level) {
    int random = Integer.parseInt(StringUtil.getFixLenthRandom(4));
    StringBuffer buffer = new StringBuffer();
    buffer.append(topCode);
    buffer.append(level);
    buffer.append(random);
    return buffer.toString();
  }

  /**
   * 功能：生成套餐ID.
   */
  public static String generateGoodsId(String topCode, int serialNum) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(topCode);
    buffer.append(String.format("%08d", serialNum));
    return buffer.toString();
  }

  /**
   * 功能：生成6位随机数.
   */
  public static int generaterSixNum() {
    return Integer.parseInt(StringUtil.getFixLenthRandom(6));
  }

  public static String generateVisitorAccountNo(String imei) {
    String md5Str = Crypto.MD5_16bit(imei);
    String middleStr = imei.substring(imei.length() - 2, imei.length());
    String prefixStr = md5Str.substring(md5Str.length() - 4, md5Str.length());
    String subfixStr = md5Str.substring(0, 3);
    return "VV" + prefixStr + middleStr + subfixStr;
  }

  /**
   * “MF” +MD5（IMEI）的后4位+IMEI的后两位+MD5（IMEI）前三位
   */
  public static String generateMifiAccountNo(String imei) {
    String md5Str = Crypto.MD5_16bit(imei);
    String middleStr = imei.substring(imei.length() - 2, imei.length());
    String prefixStr = md5Str.substring(md5Str.length() - 4, md5Str.length());
    String subfixStr = md5Str.substring(0, 3);
    return "MF" + prefixStr + middleStr + subfixStr;
  }

  /**
   * MD5（IMEI）的后三位+IMEI的后两位+MD5（IMEI）前三位
   */
  public static String genreateMifiWifiPwd(String imei) {
    String md5Str = Crypto.MD5_16bit(imei);
    String prefixStr = md5Str.substring(md5Str.length() - 3, md5Str.length());
    String middleStr = imei.substring(imei.length() - 2, imei.length());
    String subfixStr = imei.substring(0, 3);
    return prefixStr + middleStr + subfixStr;
  }

  public static String genreateMd5Pwd(String str) {
    return Crypto.MD5_16bit(str);
  }

  /**
   * 功能：生成MIFI微信公众号用户唯一标示.
   */
  public static String generateCustomId() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("WEB");
    buffer.append(DateTimeUtil.formatDate(DateTimeUtil.getSysDate(), DateTimeUtil.DATE_NUM_FORMAT));
    buffer.append(StringUtil.getFixLenthRandom(6));
    return buffer.toString();
  }

  public static void main(String[] args) {
    System.out.println("订单号" + "：" + generateOrderNo("YX"));
    System.out.println("终端用户ID" + "：" + generateClientId("YX", "460", UserClientTypeEnum.MIFI));
    System.out.println("套餐ID" + "：" + generateGoodsId("YX", 1));
    System.out.println("客户编码" + "：" + generateOrgCode("YX", 2));
    List<Integer> list = new ArrayList<Integer>();
    list.add(3);
    list.add(2);
    list.add(5);
    list.stream().sorted().forEach(System.out::println);
    System.out.println(generateVisitorAccountNo("123456"));
    System.out.println(genreateMd5Pwd("123456"));
    System.out.println("微信公众号用户ID：" + generateCustomId());
  }
}
