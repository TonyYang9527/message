package com.alpha.message.support;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfiguration {

    @Bean(name = "smsTaskExecutor")
    public ThreadPoolTaskExecutor getSmsTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(2000);
        executor.setRejectedExecutionHandler(new AbortPolicy());
        return executor;
    }

    @Bean(name = "emailTaskExecutor")
    public ThreadPoolTaskExecutor getEmailTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(2000);
        executor.setRejectedExecutionHandler(new AbortPolicy());
        return executor;
    }

    @Bean(name = "pushTaskExecutor")
    public ThreadPoolTaskExecutor getPushTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(2000);
        executor.setRejectedExecutionHandler(new AbortPolicy());
        return executor;
    }

    @Bean(name = "siteMsgTaskExecutor")
    public ThreadPoolTaskExecutor getSiteMsgTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(2000);
        executor.setRejectedExecutionHandler(new AbortPolicy());
        return executor;
    }
}
