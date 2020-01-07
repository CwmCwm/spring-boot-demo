package com.demo.annotation.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @PropertySource org.springframework.context.annotation.PropertySource 用于加载额外的 .properties配置文件
 * 应用场景：能不用就不用，配置信息都写在 application.properties 中
 * */
@PropertySource(value="classpath:/test.properties")
@Configuration
public class PropertySourceConfiguration {

    @Value(value = "${test.name}")
    private String testName;

    @Value(value = "${test.age}")
    private String testAge;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestAge() {
        return testAge;
    }

    public void setTestAge(String testAge) {
        this.testAge = testAge;
    }

}
