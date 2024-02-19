package com.rabbit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.rabbit.mapper")
@EnableScheduling
public class RabbitAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitAdminApplication.class,args);
    }
}
