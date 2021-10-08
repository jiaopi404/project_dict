package com.jiaopi404.utils;

/**
 * Resp Tpl Constant. 返回体 code 和 msg 的模板的常量
 * 1. 常量支持属性，以及构造函数
 */
public enum RespTplConstant {
    /**
     * Ok resp tpl constant.
     */
    OK(),
    /**
     * Ok custom resp tpl constant.
     */
    OK_CUSTOM(200, null),
    /**
     * Ok post resp tpl constant.
     */
    OK_POST(200, "添加成功"),
    /**
     * Ok get resp tpl constant.
     */
    OK_GET(200, "查询成功"),
    /**
     * Ok put resp tpl constant.
     */
    OK_PUT(200, "修改成功"),
    /**
     * Ok del resp tpl constant.
     */
    OK_DEL(200, "删除成功"),
    /**
     * Error resp tpl constant.
     */
// ERROR
    ERROR(500, "服务器内部错误"),
    /**
     * Error custom resp tpl constant.
     */
    ERROR_CUSTOM(500, null),
    /**
     * 表单验证错误的错误码
     */
    ERROR_VALIDATION(555)
    ;

    private final Integer code;
    private final String msg;

    private RespTplConstant () {
        this.code = 200;
        this.msg = "请求成功";
    }

    // 单参构造函数
    private RespTplConstant (Integer code) {
        this.code = code;
        this.msg = null;
    }

    private RespTplConstant (Integer code, String msg) {
        this.code = 200;
        this.msg = "请求成功";
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public Integer getCode () {
        return this.code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg () {
        return this.msg;
    }

}
