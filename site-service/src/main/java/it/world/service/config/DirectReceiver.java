package it.world.service.config;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class DirectReceiver {
    @RabbitListener(queues = "DirectQueue")
    @RabbitHandler
    public void receiveA(Message message, Channel channel) {
        System.out.println("收到消息A：" + new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("",e);
        }
    }
}
