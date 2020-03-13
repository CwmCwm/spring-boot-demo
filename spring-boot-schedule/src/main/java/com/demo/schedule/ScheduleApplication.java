package com.demo.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @EnableScheduling 启动spring-schedule的配置，就是默认配置启动嘛，经常见到各种 @EnableXXX
 * */
@EnableScheduling
@SpringBootApplication
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

}
