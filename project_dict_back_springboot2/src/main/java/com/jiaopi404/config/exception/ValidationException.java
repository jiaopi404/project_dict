package com.jiaopi404.config.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常类，validation 验证类
 */
public class ValidationException extends RuntimeException {

    /**
     * The Err msg map.
     */
    Map<String, String> errMsgMap = new HashMap<>();

    /**
     * Instantiates a new Custom exception.
     *
     * @param errMsgMap the err msg map
     * @param errorMsg  the error msg
     */
    public ValidationException(Map<String, String> errMsgMap, String errorMsg) {
        super(errorMsg);
        this.errMsgMap = errMsgMap;
    }

    /**
     * Gets err msg map.
     *
     * @return the err msg map
     */
    public Map<String, String> getErrMsgMap() {
        return errMsgMap;
    }

    /**
     * Sets err msg map.
     *
     * @param errMsgMap the err msg map
     */
    public void setErrMsgMap(Map<String, String> errMsgMap) {
        this.errMsgMap = errMsgMap;
    }
}
