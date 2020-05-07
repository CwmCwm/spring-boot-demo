package com.demo.lifecycle.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 这个 CacheAspect 和 LogAspect 一起对 Calculator类中的方法进行AOP
 * 那会怎样呢
 *
 * 这里加上 @Order 表示顺序
 执行结果

 LogAspect.logStart(JoinPoint joinPoint) => div除法运行....参数列表是:{[4, 2]}
 CacheAspect.cacheStart(JoinPoint joinPoint) => div除法运行....参数列表是:{[4, 2]}
 ---- Calculator.div(int i, int j) => 进入到Calculator实例调用啦
 CacheAspect.cacheEnd(JoinPoint joinPoint) => div除法结束......
 CacheAspect.cacheReturn(Object result) => 除法正常返回......运行结果是:{2}
 LogAspect.logEnd(JoinPoint joinPoint) => div除法结束......
 LogAspect.logReturn(Object result) => 除法正常返回......运行结果是:{2}

 */
@Component
@Aspect
@Order(value = 2)
public class CacheAspect {

    // 因为下面每个的拦截点是一致，所以这里统一定义，方便下面统一使用，下面的表达式语法百度吧
    @Pointcut("execution(public int com.demo.lifecycle.aop.Calculator.*(..))")
    public void pointCut(){};

    /**
     * @before 代表在目标方法执行前切入,并指定在哪个方法前切入
     * @Before("execution(public int com.demo.lifecycle.aop.Calculator.*(..))") //这种写法是粒度更细
     * */
    @Before(value = "pointCut()")
    public void cacheStart(JoinPoint joinPoint){
        System.out.println("CacheAspect.cacheStart(JoinPoint joinPoint) => " + joinPoint.getSignature().getName()+"除法运行....参数列表是:{"+ Arrays.asList(joinPoint.getArgs())+"}");
    }

    @After(value = "pointCut()")
    public void cacheEnd(JoinPoint joinPoint){
        System.out.println("CacheAspect.cacheEnd(JoinPoint joinPoint) => " + joinPoint.getSignature().getName()+"除法结束......");

    }

    @AfterReturning(value="pointCut()", returning="result")
    public void cacheReturn(Object result) {
        System.out.println("CacheAspect.cacheReturn(Object result) => " + "除法正常返回......运行结果是:{"+result+"}");
    }

    // @AfterThrowing 只是获取异常，不能捕获异常，用法是获取到异常做什么事，正常业务自己去处理异常，这里只是增强
    @AfterThrowing(value="pointCut()", throwing="exception")
    public void cacheException(Exception exception) {
        System.out.println("CacheAspect.cacheException(Object result) => " + "运行异常......异常信息是:{"+exception+"}");
    }


    //@Around 和上面的  @Before,  @After, @AfterReturning, @AfterThrowing 不一起使用，即你用了 @Around 就不用 @Before,  @After, @AfterReturning, @AfterThrowing 
    //自己试验比较@Around  和 @Before与@After 的先后顺序
//	@Around("pointCut()")
//	public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//		System.out.println("@Arount:执行目标方法之前...");
//		Object obj = proceedingJoinPoint.proceed();//相当于开始调div地
//		System.out.println("@Arount:执行目标方法之后...");
//		return obj;
//	}

}
