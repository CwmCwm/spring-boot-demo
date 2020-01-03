package com.demo.lifecycle.aop;


import org.springframework.stereotype.Component;

/**
 * 计算器，用来测试debug  SpringAOP
 * */
@Component
public class Calculator {

	// 业务逻辑方法
	public int div(int i, int j) throws ArithmeticException {
		System.out.println("---- Calculator.div(int i, int j) => 进入到Calculator实例调用啦 ");
		int result = i/j;
		return result;
	}
	
}
