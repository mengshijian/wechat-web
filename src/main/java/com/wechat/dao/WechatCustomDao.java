package com.wechat.dao;

import com.wechat.entity.WechatCustom;
import org.apache.ibatis.annotations.Param;

/**
 * 功能：微信公众号用户信息表DAO. Created by mengsj on 2019/3/23.
 */
public interface WechatCustomDao {

  /**
   * 功能：根据微信授权标示获取用户对象.
   *
   * @param openId 微信授权唯一标示.
   * @return 用户对象.
   */
  WechatCustom getByOpenId(@Param("openId") String openId);

  /**
   * 增加微信用户
   */
  Integer insert(WechatCustom custom);

  /**
   * 修改微信用户信息
   */
  Integer updateByOpenId(WechatCustom custom);

}