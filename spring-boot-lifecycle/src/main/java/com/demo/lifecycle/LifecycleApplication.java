package com.demo.lifecycle;

import com.demo.lifecycle.aop.Calculator;
import com.demo.lifecycle.bean.Human;
import com.demo.lifecycle.bean.Tiger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LifecycleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(LifecycleApplication.class, args);

		// ApplicationContext接口了解一下/很重要。
		// 实验一下，自己debug看human1 和 human2 是不是同一个bean
//		Human human1 = configurableApplicationContext.getBean(Human.class);
//		Human human2 = configurableApplicationContext.getBean(Human.class);
		// FactoryBean接口的bean 用id去获取就要加 & 符号
//		Object humanFactoryBean = configurableApplicationContext.getBean("&humanFactoryBean");

		// 测试单/多例模式 和 bean的初始化方法
//		Tiger tiger1 = configurableApplicationContext.getBean(Tiger.class);
//		Tiger tiger2 = configurableApplicationContext.getBean(Tiger.class);
//		Object tiger3 = configurableApplicationContext.getBean("tiger");
//		Object tiger4 = configurableApplicationContext.getBean("tiger");
		// configurableApplicationContext.close(); springIOC容器关闭就会销毁bean，就会调用Tiger的destroy方法
//		configurableApplicationContext.close();

		// SpringAOP的debug，试一下 4/2, 在试一下 4/0
		Calculator calculator = configurableApplicationContext.getBean(Calculator.class);
		calculator.div(4, 2);

	}

}
