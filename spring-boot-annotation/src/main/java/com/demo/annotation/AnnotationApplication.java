package com.demo.annotation;

import com.demo.annotation.configuration.PropertySourceConfiguration;
import com.demo.common.Human;
import com.demo.common.Lion;
import com.demo.common.Tiger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Repository;


@Repository
@SpringBootApplication
public class AnnotationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(AnnotationApplication.class, args);
//		Human human = configurableApplicationContext.getBean(Human.class);
//		System.out.println(human);

//		Tiger tiger = configurableApplicationContext.getBean(Tiger.class);
//		System.out.println(tiger);

//		Lion lion = configurableApplicationContext.getBean(Lion.class);
//		System.out.println(lion);

		PropertySourceConfiguration propertySourceConfiguration = configurableApplicationContext.getBean(PropertySourceConfiguration.class);
		System.out.println(propertySourceConfiguration.getTestName());
		System.out.println(propertySourceConfiguration.getTestAge());

	}

}
