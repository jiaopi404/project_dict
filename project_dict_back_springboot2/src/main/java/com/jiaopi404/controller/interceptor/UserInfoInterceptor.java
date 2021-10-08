package com.jiaopi404.controller.interceptor;

import com.jiaopi404.config.exception.GraceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 示例版 用户信息验证的 拦截器
 *
 * <p>1. HandlerInterceptor</p>
 * <p>preHandle</p>
 * <p>postHandle</p>
 * <p>afterCompletion</p>
 * <p>2. 请求拦截器返回 false 时，无数据返回</p>
 */
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO: 验证 用户名和 token
        log.info("拦截器拦截到了: " + request.getRequestURI());
        // return false 会导致无数据返回，就很难受，得研究下如何整
        // 可抛出异常，中断后续的执行
        GraceException.display("拦截器拦截到了: " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
