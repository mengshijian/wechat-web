package com.wechat.web.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:ice
 * @Date: 2018/7/20 17:09
 */
public class Rsp implements Serializable {

  private static final long serialVersionUID = 469235360237725093L;
  private boolean success;
  private int command;//bos-app-web与终端交互用.
  private int code;//错误码
  private String desc;//错误描述
  private Object data;//响应正文

  private Rsp() {

  }

  public static Rsp transEnd(int command) {//成功
    Rsp rsp = new Rsp();
    rsp.setSuccess(true);
    rsp.setCommand(command);
    return rsp;
  }

  public static Rsp transEnd(int command, int code, String desc) {
    Rsp rsp = new Rsp();
    rsp.setSuccess(true);
    rsp.setCommand(command);
    rsp.setCode(code);
    rsp.setDesc(desc);
    return rsp;
  }

  public static Rsp transEnd(int command, int code, String desc, Object eList) {
    Rsp rsp = new Rsp();
    rsp.setSuccess(true);
    rsp.setCommand(command);
    rsp.setCode(code);
    rsp.setDesc(desc);
    rsp.setData(eList);
    return rsp;
  }

  /**
   * 返回参数
   */
  public static Rsp transEnd(int code, String desc) {
    Rsp rsp = new Rsp();
    rsp.setSuccess(true);
    rsp.setCode(code);
    rsp.setDesc(desc);
    return rsp;
  }

  /**
   * 返回参数,有正文
   */
  public static Rsp transEnd(int code, String desc, Object obj) {
    Rsp rsp = new Rsp();
    rsp.setSuccess(true);
    rsp.setCode(code);
    rsp.setDesc(desc);
    rsp.setData(obj);
    return rsp;
  }

  /**
   * 返回参数，有正文,带记录条数.
   */
  public static Rsp transEnd(int code, String desc, Object resultData, long total) {
    Rsp rsp = new Rsp();
    rsp.setSuccess(true);
    rsp.setCode(code);
    rsp.setDesc(desc);
    Map<String, Object> data = new HashMap<>();
    data.put("rows", resultData);
    data.put("total", total);
    rsp.setData(data);
    return rsp;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public int getCommand() {
    return command;
  }

  public void setCommand(int command) {
    this.command = command;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String toString() {
    JSONObject jsonObject = (JSONObject) JSONObject.toJSON(this);
    return jsonObject.toString();
  }

}
