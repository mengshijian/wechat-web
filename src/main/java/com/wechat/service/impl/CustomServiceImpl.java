package com.wechat.service.impl;

import com.wechat.dao.CustomDao;
import com.wechat.entity.Custom;
import com.wechat.service.CustomService;
import com.wechat.web.utils.RpcFailReasonEnum;
import com.wechat.web.vo.RpcFailResult;
import com.wechat.web.vo.RpcResult;
import com.wechat.web.vo.RpcSuccessResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by mjg on 2019/3/22.
 */
@Service
public class CustomServiceImpl implements CustomService {

  @Resource
  private CustomDao customDao;

  @Override
  public RpcResult<Custom> getCustomByPhone(String phoneNumber) {
    Custom custom = customDao.getCustomByPhone(phoneNumber);
    if (custom != null) {
      return new RpcSuccessResult<>(custom);
    }
    return new RpcFailResult<>(RpcFailReasonEnum.EMPTY.getCode());
  }

  @Override
  public RpcResult<Custom> getCustomByOpenId(String openId) {
    Custom custom = customDao.getCustomByOpenId(openId);
    if (custom != null) {
      return new RpcSuccessResult<>(custom);
    }
    return new RpcFailResult<>(RpcFailReasonEnum.EMPTY.getCode());
  }

  @Override
  public RpcResult<Custom> getCustomByCustomId(String customId) {
    Custom custom = customDao.getCustomByCustomId(customId);
    if (custom != null) {
      return new RpcSuccessResult<>(custom);
    }
    return new RpcFailResult<>(RpcFailReasonEnum.EMPTY.getCode());
  }

  @Override
  public RpcResult<Integer> updateCustom(Custom custom) {
    int counts = customDao.updateCustom(custom);
    return new RpcSuccessResult<>(counts);
  }

  @Override
  public RpcResult<Integer> insertCustom(Custom custom) {
    int counts = customDao.insertCustom(custom);
    return new RpcSuccessResult<>(counts);
  }

  @Override
  public RpcResult<String> checkLoginKey(String phoneNumber, String loginPwd) {
    Custom custom = customDao.checkLoginKey(phoneNumber, loginPwd);
    if (custom != null) {
      return new RpcSuccessResult<>(custom.getCustomId());
    }
    return new RpcFailResult<>(RpcFailReasonEnum.EMPTY.getCode());
  }
}