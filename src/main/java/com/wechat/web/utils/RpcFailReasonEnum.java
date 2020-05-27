package com.wechat.web.utils;

/**
 * 功能：Dubbo的RPC接口操作失败原因枚举类(包括RPC服务失败原因，和JS异步请求响应结果失败原因). Created by mjg on 2017/7/17.
 */
public enum RpcFailReasonEnum {

  //错误码为 100+ 标示数据错误；200+ 标示RPC服务错误；300+ 标示客户端请求参数错误.
  SUCCEED(0x0000, ErrLevelEnum.DEBUG, ErrorTypeEnum.ERROR_NONE, 0, "SUCCEED_RESULT",
    "common.toastr.success", "操作成功"),
  DUPLICATE(0x2200, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EXISTED, 0, "DUPLICATE_RESULT",
    "common.rpc.repeat",
    "数据已存在"),
  ERR_DUPLICATE_ZONE(0x2201, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EXISTED, 0,
    "ERR_DUPLICATE_ZONE", "", "存在重复的专区名称或排序"),


  //数据为空类异常编码.
  EMPTY(0x2300, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 0, "EMPTY_RESULT",
    "common.rpc.unexist", "查无数据",
    "39"),//bos-app-web模块用,终端有调用，不可修改值.,
  ERR_REGISTER_NOT_FOUND_ORG_ERROR(0x2301, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 1,
    "ERR_REGISTER_NOT_FOUND_ORG_ERROR", "", "未查询到默认以及运营商编码", "10102"),
  ERR_CLIENT_LOGIN_ERROR(0x2302, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 2,
    "ERR_CLIENT_LOGIN_ERROR", "", "登录失败/未查询到用户信息", "10201"),
  ERR_UPDATE_PWD_NOT_FOUNDUSER_ERROR(0x2303, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 3,
    "ERR_UPDATE_PWD_NOT_FOUNDUSER_ERROR", "", "根据手机号码未找到用户", "10503"),
  ERR_ACCOUNT_NOT_FOUND_ERROR(0x2304, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 4,
    "ERR_ACCOUNT_NOT_FOUND_ERROR", "", "未查询到账户信息", "20101"),
  ERR_USER_GOODS_NOT_FOUND_ERROR(0x2305, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 5,
    "ERR_USER_GOODS_NOT_FOUND_ERROR", "", "未查询到用户套餐信息", "20212"),
  ERR_USER_NOT_FOUND_ERROR(0x2306, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 6,
    "ERR_USER_NOT_FOUND_ERROR", "", "未查询到用户信息", "20801"),
  ERR_GOODS_NOT_FOUND_ERROR(0x2307, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 7,
    "ERR_GOODS_NOT_FOUND_ERROR", "", "套餐资源不存在", "30201"),
  ERR_GOODS_IS_DEFAULT(0x2308, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 8,
    "ERR_GOODS_IS_DEFAULT", "", "该套餐为本地套餐", "30202"),
  ERR_NOT_FOUND_BANNER_ERROR(0x2309, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 9,
    "ERR_NOT_FOUND_BANNER_ERROR", "", "找不到Banner信息", "40406"),
  ERR_NOT_FOUND_VERSION_ERROR(0x230a, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 10,
    "ERR_NOT_FOUND_VERSION_ERROR", "", "找不到版本信息", "40501"),
  ERR_NOT_FOUND_IP_ERROR(0x230b, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 11,
    "ERR_NOT_FOUND_IP_ERROR", "", "未找到IP信息", "40601"),
  ERR_ORDER_CREATE_GOODS_NOT_FOUND(0x230c, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 12,
    "ERR_ORDER_CREATE_GOODS_NOT_FOUND", "", "套餐信息不存在", "50104"),
  ERR_TASK_NOT_FOUND(0x230d, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 13,
    "ERR_TASK_NOT_FOUND", "", "未查询到任务信息"),
  ERR_SIMCARD_NOT_FOUND(0x230e, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 14,
    "ERR_SIMCARD_NOT_FOUND", "", "根据iccid找不到sim信息"),
  ERR_NOT_FOUND_ZONE_ERROR(0x230f, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 15,
    "ERR_NOT_FOUND_ZONE_ERROR", "", "未找到对应的专区信息"),
  ERR_SIMCARD_NOT_FOUND_USING_IMSI(0x2310, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 16,
    "ERR_SIMCARD_NOT_FOUND",
    "", "根据imsi找不到sim信息"),
  ERR_CUSTOM_NO_PHONE(0x2311, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 17,
    "ERR_CUSTOM_NO_PHONE",
    "", "用户未绑定手机号"),
  ERR_DEVICE_NOT_FOUND_ERROR(0x2312, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 6,
    "ERR_DEVICE_NOT_FOUND_ERROR", "", "未查询到设备信息"),


  //请求参数类型异常编码.
  FAILED_PARAM(0x2400, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 0, "FAILED_PARAM",
    "common.rpc.params",
    "请求参数异常"),
  ERR_NO_PASSWD_ERR(0x2401, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 1, "ERR_NO_PASSWD_ERR",
    "", "请传入用户密码"),
  ERR_NO_PHONE_NUM_ERR(0x2402, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 2,
    "ERR_NO_PHONE_NUM_ERR", "", "请传入用户号码"),
  ERR_BANNER_TITLE_DUPLICATE_ERROR(0x2403, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 3,
    "ERR_BANNER_TITLE_DUPLICATE_ERROR", "", "banner标题重复"),
  ERR_MISSING_PARAM_ERROR(0x2404, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 4,
    "ERR_MISSING_PARAM_ERROR", "", "缺少必要的参数"),
  ERR_NOT_FOUND_IMEI(0x2405, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 5,
    "ERR_NOT_FOUND_IMEI", "", "没有找到手机IMEI"),
  ERR_NOT_FOUND_COMMAND(0x2406, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 6,
    "ERR_NOT_FOUND_COMMAND", "", "没有找到COMMAND"),
  ERR_NOT_FOUND_METHOD(0x2407, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 7,
    "ERR_NOT_FOUND_METHOD", "", "没有找到COMMAND对应方法"),
  ERR_NOT_FOUND_USERID(0x2408, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 8,
    "ERR_NOT_FOUND_USERID", "", "没有找到用户标识"),
  ERR_REGISTER_VALIDATECODE_ERROR(0x2409, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 9,
    "ERR_REGISTER_VALIDATECODE_ERROR", "", "验证码错误/已过期", "10103"),
  ERR_REGISTER_EXIST_USER_ERROR(0x240a, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 10,
    "ERR_REGISTER_EXIST_USER_ERROR", "", "该号码已注册,请确认号码是否正确", "10104"),
  ERR_CLIENT_PWD_ERROR(0x240b, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 11,
    "ERR_CLIENT_PWD_ERROR", "", "用户密码错误", "10202"),
  ERR_FORGETPWD_VALIDATECODE_ERROR(0x240c, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 12,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "验证码错误/已过期", "10303"),
  ERR_RECOVE_PWD_ERROR(0x240d, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 13,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "重置密码不能为空", "10401"),
  ERR_UPDATE_PWD_NEW_ERROR(0x240e, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 14,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "新密码不能为空", "10402"),
  ERR_UPDATE_PWD_NOT_EQUAL_ERROR(0x240f, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 15,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "旧密码不一致", "10403"),
  ERR_UPDATE_PWD_USER_NOT_EQUAL_ERROR(0x2411, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 17,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "传入用户id与系统id不一致，不允许修改", "10406"),
  ERR_UPDATE_PWD_OLD_ERROR(0x2410, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 16,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "旧密码不能为空", "10407"),
  ERR_MISSING_USER_GOODS_ID_ERROR(0x2412, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 18,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "缺少用户套餐ID", "20204"),
  ERR_USER_IDENTIFIED_CODE_TO_LONG(0x2413, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 19,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "身份证号不能为空或者过长", "21001"),
  ERR_USER_IDENTIFIED_NAME_TO_LONG(0x2414, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 20,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "姓名不能为空或者过长", "21002"),
  ERR_NOT_FOUND_ORGCODE_ERROR(0x2415, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 21,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "组织编码不对", "40502"),
  ERR_ORDER_CREATE_PARAM_ERR(0x2416, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 22,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "参数传递错误", "50103"),
  ERR_ORDER_CREATE_COUNTS_ILLEGAL(0x2417, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 23,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "购买数量不合法", "50105"),
  ERR_ORDER_GOODS_PURCHASE_LIMIT(0x2418, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 24,
    "ERR_FORGETPWD_VALIDATECODE_ERROR", "", "限购套餐允许再次购买", "50107"),
  ERR_EXCEL_DATA_INVALID(0x2419, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 25,
    "ERR_EXCEL_DATA_INVALID", "", "excel解析异常"),
  ERR_ZONE_DISABLED(0x241a, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 26,
    "ERR_ZONE_DISABLED", "", "所在专区被禁用,不允许上架"),
  ERR_OFFLINE_GOODS(0x241b, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 27,
    "ERR_OFFLINE_GOODS", "", "非上架套餐,不允许上架"),
  ERR_BANNER_SORTNUM_DUPLICATE_ERROR(0x241c, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 28,
    "ERR_BANNER_SORTNUM_DUPLICATE_ERROR", "", "banner排序值重复"),
  ERR_PARAMS_OVER_LENGTH(0x241d, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 29,
    "ERR_PARAMS_OVER_LENGTH", "", "参数长度超出限制"),
  ERR_OBTAIN_DISABLED(0x241f, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 30,
    "ERR_OBTAIN_DISABLED", "", "获取场景被禁用,不允许上架"),
  ERR_GOODSSOURCE_DISABLED(0x2420, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 31,
    "ERR_GOODSSOURCE_DISABLED", "", "第三方套餐被禁用,不允许上架"),
  ERR_GOODSSOURCE_DISDELETE(0x2421, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 32,
    "ERR_GOODSSOURCE_DISDELETE", "", "第三方套餐被套餐关联,不允许删除"),
  ERR_GOODSSOURCE_YAXIADIS(0x2422, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 33,
    "ERR_GOODSSOURCE_YAXIADIS", "", "亚夏套餐来源不能被禁用"),
  ERR_DEVICE_NOT_USE_ERROR(0x2423, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 34,
    "ERR_GOODSSOURCE_YAXIADIS", "", "设备不可用"),
  ERR_NOT_FOUND_ORDER_ERROR(0x2424, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 35,
    "ERR_NOT_FOUND_ORDER_ERROR", "", "查无订单"),
  ERR_ORDER_EFFECTIVED_ERROR(0x2425, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 36,
    "ERR_ORDER_EFFECTIVED_ERROR", "", "订单已生效"),
  ERR_CONTRACT_TIME_ERROR(0x2426, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 37,
    "ERR_CONTRACT_TIME_ERROR", "", "合约开始时间不正确"),
  ERR_DEVICE_NOT_USER_ERROR(0x2427, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 38,
    "ERR_DEVICE_NOT_USER_ERROR", "", "设备没有被客户租出"),
  ERR_DEVICE_LENTED_ERROR(0x2428, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 39,
    "ERR_DEVICE_LENTED_ERROR", "", "设备已借出"),
  ERR_ORDER_DELETE_ERROR(0x2429, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 40,
    "ERR_ORDER_DELETE_ERROR", "", "订单不能被删除"),
  ERR_NOT_FOUND_DEVICE_ERROR(0x2430, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 41,
    "ERR_NOT_FOUND_DEVICE_ERROR", "", "查无设备"),
  ERR_NOT_FOUND_CUSTOM_ERROR(0x2431, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 42,
    "ERR_NOT_FOUND_CUSTOM_ERROR", "", "查无用户"),
  ERR_EXISTS_PHONE_ERROR(0x2432, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 43,
    "ERR_EXISTS_PHONE_ERROR", "", "电话号码已存在用户"),
  ERR_CUSTOM_DISABLE_ERROR(0x2433, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 44,
    "ERR_CUSTOM_DISABLE_ERROR", "", "客户被禁用"),
  ERR_CUSTOM_ERR_PWD(0x2434, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 45,
    "ERR_CUSTOM_ERR_PWD",
    "", "登录密码不正确"),
  ERR_DEVICE_BIND_USER(0x2435, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_EMPTY, 46,
    "ERR_DEVICE_BIND_USER",
    "", "设备已被绑定到其他用户"),
  ERR_NOT_CUSTOM_ORDER_ERROR(0x2436, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 47,
    "ERR_NOT_CUSTOM_ORDER_ERROR", "", "查无订单，或该订单不属于当前用户"),
  ERR_GOODS_UPDATE_STATUS_ERROR(0x2437, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_PARAM, 47,
    "ERR_GOODS_UPDATE_STATUS_ERROR", "", "只有未上架套餐可编辑"),


  //服务器操作类型异常.
  ERR_FAIL_ERROR(0x2500, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 0, "FAILED_SERVER",
    "common.rpc.server",
    "操作失败"),
  ERR_UPDATE_ERROR(0x2501, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 1, "ERR_UPDATE_ERROR",
    "", "状态启动中,无法被操作"),
  ERR_NUMBER_FORMATR_ERR(0x2502, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 2,
    "ERR_NUMBER_FORMATR_ERR", "", "数字转换异常"),
  ERR_REGISTER_ERROR(0x2503, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 3,
    "ERR_REGISTER_ERROR", "", "注册接口调用失败", "10101"),
  ERR_USER_FREEZING_ERROR(0x2504, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 4,
    "ERR_USER_FREEZING_ERROR", "", "用户已冻结，不允许登录", "10204"),
  ERR_UPDATE_PWD_ERR(0x2505, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 5,
    "ERR_UPDATE_PWD_ERR", "", "修改密码失败", "10404"),
  ERR_SEND_SMS_ERROR(0x2506, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 6,
    "ERR_SEND_SMS_ERROR", "", "发送短信失败", "10601"),
  ERR_SEND_SMS_TEMPLTE_ERROR(0x2507, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 7,
    "ERR_SEND_SMS_TEMPLTE_ERROR", "", "查询短信模板失败", "10602"),
  ERR_IS_REGISTER_ERROR(0x2508, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 8,
    "ERR_IS_REGISTER_ERROR", "", "查询是否注册接口失败", "10701"),
  ERR_USER_GOODS_ACTIVATE_FAIL(0x2509, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 9,
    "ERR_USER_GOODS_ACTIVATE_FAIL", "", "激活用户套餐失败", "20206"),
  ERR_USER_GOODS_ACTIVED(0x250a, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 10,
    "ERR_USER_GOODS_ACTIVED", "", "激活失败,套餐已被激活", "20207"),
  ERR_USER_GOODS_NOT_ACTIVED(0x250b, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 11,
    "ERR_USER_GOODS_NOT_ACTIVED", "", "套餐未激活", "20208"),
  ERR_ANALYSIS_LK_ORDER_REQ_FALSE(0x250c, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 12,
    "ERR_ANALYSIS_LK_ORDER_REQ_FALSE", "", "解析领科下单请求失败", "20209"),
  ERR_ANALYSIS_LK_ORDER_RSP_FALSE(0x250d, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 13,
    "ERR_ANALYSIS_LK_ORDER_RSP_FALSE", "", "解析领科下单回复失败", "20210"),
  ERR_SAVE_SUGGEST_FAIL(0x250e, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 14,
    "ERR_SAVE_SUGGEST_FAIL", "", "保存反馈意见失败", "40403"),
  ERR_ORDER_CREATE_RATE_LIMIT(0x250f, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 15,
    "ERR_ORDER_CREATE_RATE_LIMIT", "", "订单频率生成过快", "50101"),
  ERR_ORDER_CREATE_UNKOWN(0x2510, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 16,
    "ERR_ORDER_CREATE_UNKOWN", "", "订单创建异常", "50102"),
  ERR_ORDER_CREATE_BALANCE_NOT_ENOUGH(0x2511, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 17,
    "ERR_ORDER_CREATE_BALANCE_NOT_ENOUGH", "", "账户余额不足", "50106"),
  ERR_NET_ERR_CODE(0x2512, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 18, "ERR_NET_ERR_CODE",
    "", "网络异常", "20211"),
  ERR_SYSTEM_ERROR(0x2513, ErrLevelEnum.ERROR, ErrorTypeEnum.ERROR_SERVER, 0, "ERR_SYSTEM_ERROR",
      "", "系统异常", "50106"),

  FAIL_BIND_SIMCARD(0x2513, ErrLevelEnum.INFO, ErrorTypeEnum.ERROR_SERVER, 19, "FAIL_BIND_SIMCARD",
    "common.rpc.bind", "卡已被绑定"),
  ERR_PASSWD_CHANGE_ERR(0x2514, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 20,
    "ERR_PASSWD_CHANGE_ERR", "", "用户密码已修改，请重新登录", "43"),//bos-app-web模块用,终端有调用，不可修改值.
  INUSED(0x2515, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 21, "INUSED", "common.rpc.used",
    "数据已被使用"),
  ERR_UPDATE_PWD_USER_FREEZING_ERROR(0x2516, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 22,
    "ERR_UPDATE_PWD_USER_FREEZING_ERROR", "", "用户已冻结不允许修改密码", "10405"),
  ERR_CONN_ASS(0x2517, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 23, "ERR_CONN_ASS", "",
    "请求ASS服务接口调用失败"),
  ERR_GENERATE_ACCOUNT(0x2518, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 24,
    "ERR_GENERATE_ACCOUNT", "", "账户生成失败"),
  ERR_ONLINE_GOODS(0x2519, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 25, "ERR_ONLINE_GOODS",
    "", "套餐已上架"),
  ERR_ENABLED_GOODS(0x251a, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 26,
    "ERR_ENABLED_GOODS", "", "套餐来源非禁用状态，不能被编辑"),
  ERR_UPDATE_BANNER_STATUS_ERROR(0x251b, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 27,
    "ERR_UPDATE_BANNER_STATUS_ERROR", "", "状态为启用的banner图不可被修改"),
  ERR_UPDATE_ZONE_NOT_DISABLE_ERROR(0x251c, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 28,
    "ERR_UPDATE_ZONE_NOT_DISABLE_ERROR", "", "专区存在未下架的套餐，不允许禁用"),
  ERR_UPDATE_ZONE_DISABLE_ERROR(0x251d, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 29,
    "ERR_UPDATE_ZONE_NOT_DISABLE_ERROR", "", "只有禁用的专区信息才能修改"),
  ERR_UPDATE_ZONE_EXIST_INFO_ERROR(0x251e, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 30,
    "ERR_UPDATE_ZONE_EXIST_INFO_ERROR", "", "存在名字相同或者排序相同的专区信息"),
  ERR_UPDATE_CONFIG_EXIST_INFO_ERROR(0x251f, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 31,
    "ERR_UPDATE_CONFIG_EXIST_INFO_ERROR", "", "已存在，无法输入相同的参数值"),

  ERR_SIMCARD_NOW_BE_USING(0x2600, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_SERVER, 0,
    "ERR_SIMCARD_NOW_BE_USING", "", "该卡当前正被使用"),

  ERP_ICCID_NOT_EXISTS(0x2601, ErrLevelEnum.WARNING, ErrorTypeEnum.INFO_SIMCARD, 0,
    "ERP_ICCID_NOT_EXISTS", "charge.iccid.notExists", "iccid不存在"),

  ERR_END(0x00ff, ErrLevelEnum.WARNING, ErrorTypeEnum.ERROR_NONE, 255, "ERR_END", "", "", "结束");

  private int code;//完整错误码.
  private ErrLevelEnum level;//日志级别编号.
  private ErrorTypeEnum errcode;//错误编码.
  private int subErrcodeNo;//子错误编号.
  private String subErrcode;//子错误编码.
  private String i18nKey;//国际化编码key.
  private String desc;//错误描述
  private String modalErrCode;//模块错误编码(现仅bos-app-web用到，为兼容该模块设计，特保留此属性).

  RpcFailReasonEnum(int code, ErrLevelEnum level, ErrorTypeEnum errcode, int subErrcodeNo,
                    String subErrcode, String i18nKey, String msg) {
    this.code = code;
    this.level = level;
    this.errcode = errcode;
    this.subErrcodeNo = subErrcodeNo;
    this.subErrcode = subErrcode;
    this.i18nKey = i18nKey;
    this.desc = msg;
  }

  RpcFailReasonEnum(int code, ErrLevelEnum level, ErrorTypeEnum errcode, int subErrcodeNo,
                    String subErrcode, String i18nKey, String msg, String modalErrCode) {
    this.code = code;
    this.level = level;
    this.errcode = errcode;
    this.subErrcodeNo = subErrcodeNo;
    this.subErrcode = subErrcode;
    this.i18nKey = i18nKey;
    this.desc = msg;
    this.modalErrCode = modalErrCode;
  }

  public static String getDesc(int code) {
    for (RpcFailReasonEnum failReasonEnum : RpcFailReasonEnum.values()) {
      if (failReasonEnum.getCode() == code) {
        return failReasonEnum.getDesc();
      }
    }
    return "";
  }

  public static RpcFailReasonEnum getEnum(int code) {
    for (RpcFailReasonEnum failReasonEnum : RpcFailReasonEnum.values()) {
      if (failReasonEnum.getCode() == code) {
        return failReasonEnum;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }

  public ErrLevelEnum getLevel() {
    return level;
  }

  public ErrorTypeEnum getErrcode() {
    return errcode;
  }

  public int getSubErrcodeNo() {
    return subErrcodeNo;
  }

  public String getSubErrcode() {
    return subErrcode;
  }

  public String getDesc() {
    return desc;
  }

  public String getI18nKey() {
    return i18nKey;
  }

  public String getModalErrCode() {
    return modalErrCode;
  }
}
