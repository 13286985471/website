package it.world.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableZuulProxy
@EnableFeignClients(basePackages = { "it.world.gateway.feign" })
public class SiteGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteGateWayApplication.class, args);
    }
}
