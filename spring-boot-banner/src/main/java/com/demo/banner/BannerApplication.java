package com.demo.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 配置spring-boot的启动入口，你看下面就是main方法
 * */
@SpringBootApplication
public class BannerApplication {

	/**
	 * resources/ 目录下的banner.png就是springboot启动时控制台的输出，这里用来Tomcat的图标，你可以换图片/图片格式试试
	 * resources/ 目录下的banner.txt就是springboot启动时控制台的输出，这里是hello world，你可以改一下试试
	 * */
	public static void main(String[] args) {
		SpringApplication.run(BannerApplication.class, args);
	}

}
