package com.jiaopi404.config.exception;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {
    /**
     * Instantiates a new Custom exception.
     *
     * @param errorMsg the error msg
     */
    public CustomException(String errorMsg) {
        super(errorMsg);
    }
}
