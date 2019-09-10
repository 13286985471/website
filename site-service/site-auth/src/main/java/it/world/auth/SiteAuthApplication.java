package it.world.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("it.world.auth.mapper")
public class SiteAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteAuthApplication.class, args);
    }

}
