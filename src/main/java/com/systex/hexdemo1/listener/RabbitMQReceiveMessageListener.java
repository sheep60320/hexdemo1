package com.systex.hexdemo1.listener;

import com.systex.hexdemo1.common.message.RecordMessage;
import com.systex.hexdemo1.configure.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQReceiveMessageListener {
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_NAME})
    public void receive(RecordMessage m) {
        log.info("received message as:{}", m);
    }
}