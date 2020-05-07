package com.demo.lifecycle.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 命名为 XXAspect 是大概因为 @Aspect
 *
 *  @Aspect org.aspectj.lang.annotation.Aspect 标识这是切面类，这里是日志切面类，其实就是定义切面方法，然后代理对象回调这些切面方法
 * 日志切面类的方法需要动态感知到div()方法运行,
 *   通知/切面方法:
 *     @Before 前置通知/切面:logStart(); 在我们执行div()除法之前运行(@Before)
 *     @After 后置通知/切面:logEnd();在我们目标方法div运行结束之后 ,不管有没有异常(@After)
 *     @AfterReturning 返回通知/切面:logReturn();在我们的目标方法div正常返回值后运行(@AfterReturning)
 *     @AfterThrowing 异常通知/切面:logException();在我们的目标方法div出现异常后运行(@AfterThrowing)
 *     @Around 环绕通知/切面:动态代理, 需要手动执行joinPoint.procced()其实就是执行我们的目标方法div,
 *       执行之前div()相当于前置通知, 执行之后就相当于我们后置通知(@Around)
 *       有上面四个通知/切面，就不大需要环绕通知/切面
 *
 *  好了，可以去LifecycleApplication debug SpringAOP了
 */
@Component
@Aspect
@Order(value = 1)
public class LogAspect {

    // 因为下面每个的拦截点是一致，所以这里统一定义，方便下面统一使用，下面的表达式语法百度吧
    @Pointcut("execution(public int com.demo.lifecycle.aop.Calculator.*(..))")
    public void pointCut(){};

    /**
     * @before 代表在目标方法执行前切入,并指定在哪个方法前切入
     * @Before("execution(public int com.demo.lifecycle.aop.Calculator.*(..))") //这种写法是粒度更细
     * */
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint){
        System.out.println("LogAspect.logStart(JoinPoint joinPoint) => " + joinPoint.getSignature().getName()+"除法运行....参数列表是:{"+ Arrays.asList(joinPoint.getArgs())+"}");
    }

    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println("LogAspect.logEnd(JoinPoint joinPoint) => " + joinPoint.getSignature().getName()+"除法结束......");

    }

    @AfterReturning(value="pointCut()", returning="result")
    public void logReturn(Object result) {
        System.out.println("LogAspect.logReturn(Object result) => " + "除法正常返回......运行结果是:{"+result+"}");
    }

    // @AfterThrowing 只是获取异常，不能捕获异常，用法是获取到异常做什么事，正常业务自己去处理异常，这里只是增强
    @AfterThrowing(value="pointCut()", throwing="exception")
    public void logException(Exception exception) {
        System.out.println("LogAspect.logException(Object result) => " + "运行异常......异常信息是:{"+exception+"}");
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
