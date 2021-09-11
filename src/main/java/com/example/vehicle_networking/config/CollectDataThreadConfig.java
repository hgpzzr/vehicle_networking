package com.example.vehicle_networking.config;

import com.example.vehicle_networking.thread.ReadDataThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/10 20:20
 */
@Configuration
public class CollectDataThreadConfig {

    @Bean
    public ReadDataThread getReadDataThread(){
        return new ReadDataThread("collectDataThread");
    }

    @Bean
    public ExecutorService getThreadPool(){
        return new ThreadPoolExecutor(
                5,
                10,
                20,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
    }

    @Bean
    public Map<Integer, ReadDataThread> getReadDataThreadMap(){
        return new HashMap<>();
    }
}
