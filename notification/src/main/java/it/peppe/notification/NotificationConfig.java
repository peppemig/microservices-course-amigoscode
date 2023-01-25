package it.peppe.notification;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationConfig {

    // FROM APPLICATION FILE
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    // FROM APPLICATION FILE
    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue;

    // FROM APPLICATION FILE
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    // EXCHANGE
    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }

    // QUEUE
    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificationQueue);
    }

    // BINDING
    @Bean
    public Binding internalToNotificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }
}
