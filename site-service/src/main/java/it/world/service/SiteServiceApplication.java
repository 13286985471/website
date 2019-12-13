package it.world.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("it.world.service.mapper")
public class SiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteServiceApplication.class, args);
    }

}
