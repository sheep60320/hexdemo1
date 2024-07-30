package com.systex.hexdemo1.adapter.out.queue;

import com.systex.hexdemo1.common.domain.Expense;
import com.systex.hexdemo1.common.message.RecordMessage;
import com.systex.hexdemo1.common.port.out.RecordPost;
import com.systex.hexdemo1.configure.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RecordsRabbitMQMessage implements RecordPost {
    @Autowired
    private RabbitTemplate template;

    @Override
    public void postAddMessage(Expense expense) {
        postMessage(expense, "ADD");
    }


    @Override
    public void postModifyMessage(Expense expense) {
        postMessage(expense, "MODIFY");
    }

    private void postMessage(Expense expense, String type) {
        RecordMessage message = RecordPost.getMessageFromBean(expense);
        message.setType(type);
        template.convertAndSend(RabbitmqConfig.QUEUE_NAME, message);
    }
}