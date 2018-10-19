package com.bestapp.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    public void receive(String msg) {
        log.info("Message received.. {}", msg);
    }
}
