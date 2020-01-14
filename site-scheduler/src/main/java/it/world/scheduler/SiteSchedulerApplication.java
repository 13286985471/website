package it.world.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SiteSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteSchedulerApplication.class, args);
    }

}
