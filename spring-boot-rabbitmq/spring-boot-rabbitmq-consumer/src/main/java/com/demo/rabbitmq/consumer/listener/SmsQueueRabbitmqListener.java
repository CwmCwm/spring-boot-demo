package com.demo.rabbitmq.consumer.listener;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 监听器是监听队列的，跟交换器没关系
 * */
@Component
@RabbitListener(queues = "smsQueue")
public class SmsQueueRabbitmqListener {

    @RabbitHandler
    public void process(Map smsMessage) {
        System.out.println("给" + smsMessage.get("phoneNumber") + "发送短息");
        System.out.println("spring-boot-rabbitmq-consumer消费者收到消息 : " + smsMessage.toString());
    }

}
