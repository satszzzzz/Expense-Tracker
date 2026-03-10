package com.example.UserService.deserializer;

import com.example.UserService.entities.UserInfoEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDtoDeserializer implements Deserializer<UserInfoEvent>
{

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public UserInfoEvent deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(bytes);
        UserInfoEvent userDto = null;
        try
        {
            userDto = objectMapper.readValue(bytes, UserInfoEvent.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public void close() {

    }
}
