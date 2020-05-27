package com.wechat.web.vo;

/**
 * Rpc调用失败返回数据
 *
 * @author Charles
 * @date 2017/4/20
 */
public class RpcFailResult<T> extends RpcResult<T> {

  private static final long serialVersionUID = 8932467757840933341L;

  public RpcFailResult() {
    setSuccess(false);
    setDesc(DESC_FAIL);
  }

  public RpcFailResult(String desc) {
    setSuccess(false);
    setDesc(desc);
  }

  /**
   * RPC调用失败返回错误原因构造器
   */
  public RpcFailResult(int code) {
    setCode(code);
    setSuccess(false);
    setDesc(DESC_FAIL);
  }

  public RpcFailResult(int code, String desc) {
    setCode(code);
    setSuccess(false);
    setDesc(desc);
  }
}
