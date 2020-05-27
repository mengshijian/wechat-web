package com.wechat.service.impl;

import com.wechat.dao.WechatCustomDao;
import com.wechat.entity.Custom;
import com.wechat.entity.WechatCustom;
import com.wechat.service.CustomService;
import com.wechat.service.WechatCustomService;
import com.wechat.web.utils.BooleanEnum;
import com.wechat.web.utils.UserStatusEnum;
import com.wechat.web.vo.RpcFailResult;
import com.wechat.web.vo.RpcResult;
import com.wechat.web.vo.RpcSuccessResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by mengsj on 2019/3/22.
 */
@Service
public class WechatCustomServiceImpl implements WechatCustomService {

  @Resource
  private WechatCustomDao wechatCustomDao;

  @Autowired
  private CustomService customService;


  @Override
  public RpcResult<WechatCustom> getByOpenId(String openId) {
    if (StringUtils.isNotBlank(openId)) {
      WechatCustom custom = wechatCustomDao.getByOpenId(openId);
      return new RpcSuccessResult<>(custom);
    }
    return new RpcFailResult<>("000");
  }


  @Override
  @Transactional
  public RpcResult<Integer> updateByOpenId(WechatCustom wxcustom) {
    int counts = wechatCustomDao.updateByOpenId(wxcustom);
    Custom custom = customService.getCustomByOpenId(wxcustom.getOpenId()).getData();
    custom.setLastUpdateTime(wxcustom.getLastUpdateTime());
    counts = customService.updateCustom(custom).getData();
    return new RpcSuccessResult<>(counts);
  }

  @Override
  @Transactional
  public RpcResult<Integer> insert(WechatCustom custom) {
    int counts = wechatCustomDao.insert(custom);
    Custom ct = new Custom();
    ct.setCreateTime(custom.getCreateTime());
    ct.setLastUpdateTime(custom.getLastUpdateTime());
    ct.setIsCertified(BooleanEnum.FALSE.getCode());
    ct.setStatus(UserStatusEnum.NORMAL.getCode());
    ct.setCustomId(custom.getCustomId());
    ct.setOpenId(custom.getOpenId());
    counts = customService.insertCustom(ct).getData();
    return new RpcSuccessResult<>(counts);
  }
}