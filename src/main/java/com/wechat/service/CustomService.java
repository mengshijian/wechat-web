package com.wechat.service;

import com.wechat.entity.Custom;
import com.wechat.web.vo.RpcResult;

/**
 * 功能：微信公众号用户接口服务. Created by mjg on 2019/3/22.
 */
public interface CustomService {

  /**
   * 功能：获取指定手机号码的用户.
   *
   * @param phoneNumber 手机号码
   * @return 用户对象.
   */
  RpcResult<Custom> getCustomByPhone(String phoneNumber);

  /**
   * 功能：获取指定微信授权唯一标示的用户.
   *
   * @param openId 微信授权唯一标示
   * @return 用户对象.
   */
  RpcResult<Custom> getCustomByOpenId(String openId);

  /**
   * 功能：获取指定用户ID的用户.
   *
   * @param customId 用户ID
   * @return 用户对象.
   */
  RpcResult<Custom> getCustomByCustomId(String customId);

  /**
   * 功能：更新微信公众号用户信息.
   *
   * @param custom 用户信息对象.
   * @return 操作数据条数.
   */
  RpcResult<Integer> updateCustom(Custom custom);

  /**
   * 功能：新增微信公众号用户.
   *
   * @param custom 用户信息对象.
   * @return 操作数据条数.
   */
  RpcResult<Integer> insertCustom(Custom custom);

  /**
   * 功能：检查公众号用户登录密码是否正确，并返回用户ID.
   *
   * @param phoneNumber 手机号码.
   * @param loginPwd    登录密码.
   * @return 用户ID.
   */
  RpcResult<String> checkLoginKey(String phoneNumber, String loginPwd);
}