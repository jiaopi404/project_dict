package com.jiaopi404.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 自定义 任务 （定时任务）类
 * <p>1. Configuration 被扫描</p>
 * <p>2. EnableScheduling 开启 定时任务功能 (Schedule 安排，为...安排时间</p>
 * <p>3. 方法上添加注解 Scheduled，设置 cron 属性值</p>
 * <p>4. 获取当前时间 LocalDateTime.now()</p>
 * <p>5. cron n. (计算机) 计时程序</p>
 * <p>6. 分布式系统中有分布式系统独有的异步任务（分布式系统无法使用此种方式开启异步任务）</p>
 * <p>常用的 定时任务 表达式：</p>
 * <p>0 0 2 1 * ? *：表示在每月的1日的凌晨2点调整任务；</p>
 * <p>0 15 10 ? * MON-FRI：表示周一到周五每天上午10:15执行作业；</p>
 * <p>0 15 10 ? 6L 2002-2006：表示2002-2006年的每个月的最后一个星期五上午10:15执行作；</p>
 * <p>0 0 10,14,16 * * ?：每天上午10点，下午2点，4点；</p>
 * <p>0 0/30 9-17 * * ?：朝九晚五工作时间内每半小时；</p>
 * <p>0 0 12 ? * WED：表示每个星期三中午12点；</p>
 * <p>0 0 12 * * ?：每天中午12点触发；</p>
 * <p>0 15 10 ? * *：每天上午10:15触发；</p>
 * <p>0 15 10 * * ?：每天上午10:15触发；</p>
 * <p>0 15 10 * * ? *：每天上午10:15触发；</p>
 * <p>0 15 10 * * ? 2005：2005年的每天上午10:15触发；</p>
 * <p>0 * 14 * * ?：在每天下午2点到下午2:59期间的每1分钟触发；</p>
 * <p>0 0/5 14 * * ?：在每天下午2点到下午2:55期间的每5分钟触发；</p>
 * <p>0 0/5 14,18 * * ?：在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发；</p>
 * <p>0 0-5 14 * * ?：在每天下午2点到下午2:05期间的每1分钟触发；</p>
 * <p>0 10,44 14 ? 3 WED：每年三月的星期三的下午2:10和2:44触发；</p>
 * <p>0 15 10 ? * MON-FRI：周一至周五的上午10:15触发；</p>
 * <p>0 15 10 15 * ?：每月15日上午10:15触发；</p>
 * <p>0 15 10 L * ?：每月最后一日的上午10:15触发；</p>
 * <p>0 15 10 ? * 6L：每月的最后一个星期五上午10:15触发；</p>
 * <p>0 15 10 ? * 6L 2002-2005：2002年至2005年的每月的最后一个星期五上午10:15触发；</p>
 * <p>0 15 10 ? * 6#3：每月的第三个星期五上午10:15触发；</p>
 */
//@Configuration
//@EnableScheduling
@Slf4j
public class CusTask {

    /**
     * Publish msg.
     */
    @Scheduled(cron = "*/5 * * * * ?") // 标识定时任务，每隔5秒执行一次；
    public void publishMsg () {
        log.warn("定时任务开启：" + LocalDateTime.now());
        // log 内容
        // 2021-09-30 12:19:25.006  WARN 27802 --- [   scheduling-1] com.jiaopi404.utils.CusTask  : 定时任务开启：2021-09-30T12:19:25.006036
    }
}
