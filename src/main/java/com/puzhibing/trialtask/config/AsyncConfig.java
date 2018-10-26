package com.puzhibing.trialtask.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

//所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
@Configuration
public class AsyncConfig implements SchedulingConfigurer {

    //设定一个长度5的定时任务线程池
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
    }

}
