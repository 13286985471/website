package it.world.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SiteRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteRegistryApplication.class, args);
    }

}
