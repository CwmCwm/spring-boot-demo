package com.demo.annotation.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * org.springframework.context.annotation.Condition 配合 @Conditional 使用
 * 条件判断的逻辑
 * */
public class LinuxCondition implements Condition {

    /**
     * ConditionContext 和 AnnotatedTypeMetadata 能获取什么信息，看方法，打印试试
     * 这里判断项目运行时的操作系统
     * */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        if(environment.getProperty("os.name").contains("linux")){
            return true;
        }
        return false;
    }

}
