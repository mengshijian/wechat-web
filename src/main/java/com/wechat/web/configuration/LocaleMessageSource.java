package com.wechat.web.configuration;

import com.wechat.web.utils.RpcFailReasonEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class LocaleMessageSource {

  @Resource
  private MessageSource messageSource;

  public String getMessage(String keyword, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(keyword, args, locale);
  }

  /**
   * 功能：获取指定RPC的国际化内容.
   */
  public String getRpcMessage(RpcFailReasonEnum reasonEnum) {
    String keyword = null;
    switch (reasonEnum) {
      case SUCCEED:
        keyword = "common.toastr.success";
        break;
      case DUPLICATE:
        keyword = "common.rpc.repeat";
        break;
      case EMPTY:
        keyword = "common.rpc.unexist";
        break;
      case INUSED:
        keyword = "common.rpc.used";
        break;
      case FAIL_BIND_SIMCARD:
        keyword = "common.rpc.bind";
        break;
      case ERR_FAIL_ERROR:
        keyword = "common.rpc.server";
        break;
      case FAILED_PARAM:
        keyword = "common.rpc.params";
        break;
      default:
        //如果没有指定国际化标示，则返回其描述.
        if (StringUtils.isEmpty(reasonEnum.getI18nKey())) {
          return reasonEnum.getDesc();
        } else {
          keyword = reasonEnum.getI18nKey();
        }
        break;
    }
    //如果国际化配置内容为空，则返回其描述.
    Locale locale = LocaleContextHolder.getLocale();
    String result = messageSource.getMessage(keyword, new Object[0], locale);
    if (StringUtils.isEmpty(result)) {
      result = reasonEnum.getDesc();
    }
    return result;
  }
}
