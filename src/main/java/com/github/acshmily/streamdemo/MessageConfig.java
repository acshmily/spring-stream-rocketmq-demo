package com.github.acshmily.streamdemo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface MessageConfig {
    @Output("prod-out")
    MessageChannel send();
    @Input("prod-out")
    SubscribableChannel receiver();
    @Output("delay-out")
    MessageChannel delay();
    @Input("delay-out")
    SubscribableChannel delayreceiver();
}
