package it.peppe.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQMessageProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey){
        log.info("Publishing to {} using routing key {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routing key {}. Payload: {}", exchange, routingKey, payload);
    }

}
