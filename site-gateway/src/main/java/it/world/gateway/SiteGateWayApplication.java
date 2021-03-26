package it.world.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SiteGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteGateWayApplication.class, args);
    }
}
