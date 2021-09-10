package com.example.vehicle_networking.config;

import com.example.vehicle_networking.thread.ReadDataThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/10 20:20
 */
@Configuration
public class CollectDataThreadConfig {

    @Bean
    public ReadDataThread getReadDataThread(){
        return new ReadDataThread();
    }
}
