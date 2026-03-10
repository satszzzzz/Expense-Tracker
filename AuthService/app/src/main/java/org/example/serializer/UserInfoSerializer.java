package org.example.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.model.UserInfoEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoEvent> {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, UserInfoEvent data){  // ✅ null check
        byte[] bytes = null;
        try {
            System.out.println(objectMapper.writeValueAsString(data)+" "+1);
            System.out.println(data.toString()+" "+3);
            bytes = objectMapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8);
            System.out.println(objectMapper.writeValueAsString(data)+" "+1);
            return bytes; // ✅ writeValueAsBytes is cleaner
        } catch (Exception e) {
            System.out.println(e+" "+2);
        }
        return bytes;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, UserInfoEvent data) {

        return new byte[0];
    }

    @Override
    public void close() {

    }
}
