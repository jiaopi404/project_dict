package com.jiaopi404.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * 异步任务的实例；
 * <p>1. EnableAsync 类注解</p>
 * <p>2. Async 方法注解</p>
 * <p>3. 返回值为 {@code Future<T>}</p>
 * <p>4. 同定时任务一样，在分布式系统中无法适用;</p>
 */
//@Component
//@EnableAsync // 开启异步任务
@Slf4j
public class CusAsyncTask {

    /**
     * Async get sth.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Async
    public void asyncGetSth () throws InterruptedException {
        Thread.sleep(5000);
        log.warn("这个是异步调用之后的结果");
    }
}
