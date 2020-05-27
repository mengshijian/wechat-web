package com.wechat.dao;

import com.wechat.entity.Custom;
import org.apache.ibatis.annotations.Param;

/**
 * 功能：微信公众号用户信息表DAO. Created by mjg on 2019/3/23.
 */
public interface CustomDao {

  /**
   * 功能：根据手机号获取用户对象.
   *
   * @param phoneNumber 手机号码.
   * @return 用户对象.
   */
  Custom getCustomByPhone(String phoneNumber);

  /**
   * 功能：根据微信授权标示获取用户对象.
   *
   * @param openId 微信授权唯一标示.
   * @return 用户对象.
   */
  Custom getCustomByOpenId(String openId);

  /**
   * 功能：根据用户ID获取用户对象.
   *
   * @param customId 用户ID.
   * @return 用户对象.
   */
  Custom getCustomByCustomId(String customId);

  /**
   * 功能：修改用户对象属性．
   *
   * @param custom 　用户对象.
   */
  Integer updateCustom(Custom custom);

  /**
   * 功能：新增用户对象.
   *
   * @param custom 用户对象.
   */
  Integer insertCustom(Custom custom);

  /**
   * 功能：根据手机号和登录密码获取用户对象.
   *
   * @param phoneNumber 手机号码.
   * @param loginPwd    登录密码.
   * @return 用户对象.
   */
  Custom checkLoginKey(@Param("phoneNumber") String phoneNumber,
                       @Param("loginPwd") String loginPwd);
}