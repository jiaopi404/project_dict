package com.jiaopi404.config.exception;

import com.jiaopi404.utils.ResultV0;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 优雅的异常处理工具
 * <p>===== Part I =====</p>
 * <p>1. 异常要优雅得处理</p>
 * <p>2. ControllerAdvice 注解，基于 AOP 原理，会被扫描到 (实现了 Component) </p>
 * <p>3. 并不能拦截未抛出的错误，需要手动得抛出 Exception 才会拦截得到</p>
 * <p>4. 可以通过顶级类 Exception 拦截到抛出的其他衍生异常类，如 IOException</p>
 * <p>5. ExceptionHandler 默认拦截顶级类 Exception</p>
 * <p>===== Part II =====</p>
 * <p>拦截器与自定义异常：</p>
 * <p>1. 创建 自定义异常类 CustomException</p>
 * <p>2. create class: GraceException 用于优雅得抛出自定义异常</p>
 * <p>3. 在拦截器中调用 GraceException.display 静态方法手动抛出 自定义异常</p>
 * <p>4. 在 ExceptionHandler 中，@ExceptionHandler(CustomException.class) 监听异常，返回响应体</p>
 */
@ControllerAdvice
@Slf4j
public class GraceExceptionHandler {

    /**
     * Default exception handler result v 0.
     *
     * @param e [Exception]
     * @return the result v 0
     */
    @ExceptionHandler // 是否可以省略 Exception.class? 可省略
    @ResponseBody
    public ResultV0 defaultExceptionHandler (Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return ResultV0.ERROR(e.getMessage());
    }

    /**
     * 优雅的 错误 handler，处理 CustomException 自定义异常类
     *
     * @param ce the CustomException
     * @return the result v 0
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResultV0 graceExceptionHandler (CustomException ce) {
        ce.printStackTrace();
        log.error(ce.getMessage());
        return ResultV0.ERROR(ce.getMessage());
    }

    /**
     * 优雅的 错误 handler，处理 CustomException 自定义异常类
     *
     * @param ve the CustomException
     * @return the result v 0
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResultV0 validationExceptionHandler (ValidationException ve) {
//        ve.printStackTrace();
        log.error(ve.getMessage());
        return ResultV0.VALIDATION_ERROR(ve.getErrMsgMap(), ve.getMessage());
    }
}
