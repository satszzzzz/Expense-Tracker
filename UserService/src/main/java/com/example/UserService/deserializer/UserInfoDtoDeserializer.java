package com.example.UserService.deserializer;

import com.example.UserService.entities.UserInfoDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDtoDeserializer implements Deserializer<UserInfoDto>
{

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public UserInfoDto deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDto user = null;
        try
        {
            user = objectMapper.readValue(bytes, UserInfoDto.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
