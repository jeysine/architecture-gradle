package com.jeysine.process.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(10);
        // 设置最大线程数
        executor.setMaxPoolSize(20);
        // 设置队列容量
        executor.setQueueCapacity(200);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(120);
        // 设置默认线程名称
        executor.setThreadNamePrefix("event-");

        executor.setAwaitTerminationSeconds(60);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        return executor;
    }
}