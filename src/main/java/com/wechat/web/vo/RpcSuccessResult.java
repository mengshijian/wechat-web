package com.wechat.web.vo;

/**
 * Rpc调用成功返回数据
 *
 * @author Charles
 * @date 2017/4/20
 */
public class RpcSuccessResult<T> extends RpcResult<T> {

  private static final long serialVersionUID = -4246882155738992564L;

  /**
   * 有返回数据
   */
  public RpcSuccessResult(T data) {
    setSuccess(true);
    setDesc(DESC_SUCCESS);
    setData(data);
  }

  /**
   * 无返回数据
   */
  public RpcSuccessResult() {
    setSuccess(true);
    setDesc(DESC_SUCCESS);
  }
}
