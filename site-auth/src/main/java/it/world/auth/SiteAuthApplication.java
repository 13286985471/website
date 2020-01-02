package it.world.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringCloudApplication
@EnableAuthorizationServer
@EnableFeignClients(basePackages = { "it.world.auth.feign" })
public class SiteAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteAuthApplication.class, args);
    }

}
