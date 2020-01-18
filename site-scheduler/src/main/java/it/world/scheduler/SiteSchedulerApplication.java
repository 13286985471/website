package it.world.scheduler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 该服务用于定时任务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("it.world.scheduler.mapper")
public class SiteSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteSchedulerApplication.class, args);
    }

}
