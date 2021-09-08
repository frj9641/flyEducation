package com.frj.flyeducation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FlyeducationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlyeducationApplication.class, args);
    }
}
