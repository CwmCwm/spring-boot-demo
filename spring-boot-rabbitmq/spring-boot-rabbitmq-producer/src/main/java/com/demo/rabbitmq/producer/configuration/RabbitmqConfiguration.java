package com.demo.rabbitmq.producer.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

    /**
     这里启动 spring-boot-rabbitmq-producer 后会检查rabbitmq是否有名为 smsQueue 的队列，没有就创建，有就不用创建
     * */
    @Bean
    public Queue smsQueue() {
        // 可以看 org.springframework.amqp.core.Queue 的构造方法
        // durable: 默认是true，队列会持久化到磁盘上。
        // exclusive: 默认是false。 true是只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete: 默认是false，当没有生产者或者消费者使用此队列，该队列不会自动删除。
        // 这里配置使用默认参数值，你可试一下其他参数值，可能在启动spring-boot-rabbitmq-consumer时抛错
        // 具体实操改变参数值看看会有什么影响，从而理解参数语义
        return new Queue("smsQueue",true,false,false);
    }

    /**
     起个名为 smsExchange 的Exchange
     我用 TopicExchange 类型的，因为 TopicExchange可以实现direct和fanout类型
     * */
    @Bean
    TopicExchange smsExchange() {
        // 可以看org.springframework.amqp.core.TopicExchange的构造方法
        return new TopicExchange("smsExchange");
    }

    /**
     将 smsQueue队列 和 smsExchange交换器 绑定, 并设置用于匹配键：sms
     * */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(smsQueue()).to(smsExchange()).with("sms");
    }


}
