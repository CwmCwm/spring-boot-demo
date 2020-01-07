package com.demo.annotation.configuration;

import com.demo.common.Lion;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import 导入bean到IOC容器，bean的id为全类名；感觉就是批量导入
 * 应用场景：看源码经常用到
 * */
@Import(value = {Lion.class})
@Configuration
public class ImportConfiguration {

}
