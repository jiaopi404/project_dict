package com.jiaopi404.utils;

/**
 * ResultV0 通用的 Resp 返回体类
 * 1. 复习 重载；
 * 2. 不同的状态码所代表的意义；
 * 3. TODO: 还有改动余地，添加泛型，返回值更加明确
 */
public class ResultV0 {
    /**
     * 状态码
     * 200: success
     * 500: 通用的错误
     * 501: bean 验证错误，不管多少个错误都以 map 形式返回
     * 502: 拦截器拦截到用户 token 出错
     * 555: 异常抛出错误
     * 556: 用户 qq 校验异常
     * 557: 校验用户是否在 CAS 登录，用户门票的校验
     */
    Integer code; // 状态码
    /**
     * The Data.
     */
    Object data; // 数据
    /**
     * The Msg.
     */
    String msg; // 消息

    /**
     * no args constructor;
     * 200, success
     */
    public ResultV0 () {
        this.code = RespTplConstant.OK.getCode();
        this.data = null;
        this.msg = RespTplConstant.OK.getMsg();
    }

    /**
     * all args constructor
     *
     * @param code Integer
     * @param data Object
     * @param msg  String
     */
    public ResultV0 (Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * func SUCCESS default
     *
     * @return ResultV0 result v 0
     */
    public static ResultV0 OK () {
        return new ResultV0();
    }

    /**
     * func SUCCESS data
     *
     * @param data data
     * @return ResultV0 result v 0
     */
    public static ResultV0 OK (Object data) {
        return new ResultV0(RespTplConstant.OK.getCode(), data, RespTplConstant.OK.getMsg());
    }

    /**
     * func SUCCESS data and msg
     *
     * @param data data
     * @param msg  msg
     * @return ResultV0 result v 0
     */
    public static ResultV0 OK (Object data, String msg) {
        return new ResultV0(RespTplConstant.OK_CUSTOM.getCode(), data, msg);
    }

    /**
     * func ERROR
     *
     * @param msg msg
     * @return ResultV0 result v 0
     */
    public static ResultV0 ERROR (String msg) {
        return new ResultV0(RespTplConstant.ERROR.getCode(), null, msg);
    }

    /**
     * VALIDATION_ERROR 表单验证错误的handler
     *
     * @param obj obj 错误信息对象
     * @param msg msg
     * @return ResultV0 result v 0
     */
    public static ResultV0 VALIDATION_ERROR (Object obj, String msg) {
        return new ResultV0(RespTplConstant.ERROR_VALIDATION.getCode(), obj, msg);
    }

    /**
     * func ERROR default
     *
     * @return ResultV0 result v 0
     */
    public static ResultV0 ERROR () {
        return new ResultV0(RespTplConstant.ERROR.getCode(), null, RespTplConstant.ERROR.getMsg());
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
