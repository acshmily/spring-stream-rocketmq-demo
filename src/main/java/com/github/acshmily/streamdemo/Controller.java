package com.github.acshmily.streamdemo;

import org.apache.rocketmq.common.message.MessageConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@EnableBinding(MessageConfig.class)
public class Controller {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping
    public String ok(){
        Message message = MessageBuilder.
                withPayload("helo").build();
        messageConfig.send().send(message);
        return "ok";
    }
    @GetMapping("delay")
    public String delay(){
        Message message = MessageBuilder.
                withPayload("delay")
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL,"3")
                .build();
        messageConfig.delay().send(message);
        return "ok";
    }
    @StreamListener("prod-out")
    public void receiver(@Payload Message message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

    }
    @StreamListener("delay-out")
    public void delayReceiver(@Payload Message message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

    }
    @Autowired
    private MessageConfig messageConfig;
}
