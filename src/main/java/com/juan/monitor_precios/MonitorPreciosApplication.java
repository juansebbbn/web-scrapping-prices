package com.juan.monitor_precios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitorPreciosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorPreciosApplication.class, args);
    }

}
