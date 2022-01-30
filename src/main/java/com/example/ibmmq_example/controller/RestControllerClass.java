package com.example.ibmmq_example.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Message;

@RestController
public class RestControllerClass extends MessageListenerAdapter {
    @Autowired
    private JmsOperations jmsTemplate;

    @GetMapping("send")
    public void send() {
        jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World");
    }

    @Override
    @JmsListener(destination = "DEV.QUEUE.1")
    @SneakyThrows
    public void onMessage(Message message) {
        String body = message.getBody(String.class);
        System.out.println (body);
    }
}
