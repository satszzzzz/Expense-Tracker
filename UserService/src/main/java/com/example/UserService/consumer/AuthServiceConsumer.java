package com.example.UserService.consumer;

import com.example.UserService.entities.UserInfoEvent;
import com.example.UserService.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConsumer {

    private UserService userService;
    private ObjectMapper objectMapper;

    public AuthServiceConsumer(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics="${spring.kafka.topic.name}", groupId = "userinfo-consumer-group")
    public void listen(UserInfoEvent eventData)
    {
        try
        {
            userService.createOrUpdateUser(eventData);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
