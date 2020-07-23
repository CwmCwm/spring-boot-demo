package com.demo.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 @ServletComponentScan  扫描Servlet组件，就是Filter，Listener，Servlet
 * */
@ServletComponentScan
@SpringBootApplication
public class HttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpApplication.class, args);
	}

}
