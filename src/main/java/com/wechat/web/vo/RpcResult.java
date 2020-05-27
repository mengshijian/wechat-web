package com.wechat.web.vo;

import java.io.Serializable;

/**
 * Rpc调用返回数据
 *
 * @author Charles
 * @date 2017/4/20
 */
public class RpcResult<T> implements Serializable {

  protected static final String DESC_SUCCESS = "调用成功";
  protected static final String DESC_FAIL = "调用失败";
  protected static final String DESC_EXCEPTION = "调用异常";
  private static final long serialVersionUID = 6861863936193853857L;
  /**
   * 标记调用是否成功
   */
  private boolean success;
  /**
   * 调用结果描述
   */
  private String desc;
  /**
   * 调用结果码
   */
  private int code;
  /**
   * 调用结果数据码
   */
  private T data;


  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  /**
   * 判断RPC调用结果，并获取数据
   */
  public T handResult() {
    return isSuccess() && getData() != null ? getData() : null;
  }

  @Override
  public String toString() {
    return "RpcResult{" +
      "success=" + success +
      ", desc='" + desc + '\'' +
      ", data=" + data +
      '}';
  }
}