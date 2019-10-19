package it.world.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("it.world.auth.mapper")
@EnableAuthorizationServer
public class SiteAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteAuthApplication.class, args);
    }

}