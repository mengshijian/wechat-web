package com.wechat.service;

import com.wechat.entity.WechatCustom;
import com.wechat.web.vo.RpcResult;

/**
 * 功能：微信用户接口服务. Created by mengsj on 2019/3/22.
 */
public interface WechatCustomService {

  /**
   * 功能：获取指定微信授权唯一标示的用户.
   *
   * @param openId 微信授权唯一标示
   * @return 用户对象.
   */
  RpcResult<WechatCustom> getByOpenId(String openId);

  /**
   * 功能：更新微信公众号用户信息.
   *
   * @param custom 用户信息对象.
   * @return 操作数据条数.
   */
  RpcResult<Integer> updateByOpenId(WechatCustom custom);

  /**
   * 功能：新增微信公众号用户.
   *
   * @param custom 用户信息对象.
   * @return 操作数据条数.
   */
  RpcResult<Integer> insert(WechatCustom custom);

}