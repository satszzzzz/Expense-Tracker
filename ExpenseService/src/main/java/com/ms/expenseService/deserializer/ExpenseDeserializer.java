package com.ms.expenseService.deserializer;


import com.ms.expenseService.dto.ExpenseDto;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ExpenseDto deserialize(String s, byte[] bytes) {
        if(bytes == null)
            return null;
        ExpenseDto expenseDto = null;
        ObjectMapper objectMapper= new ObjectMapper();
        try
        {
            expenseDto = objectMapper.readValue(bytes, ExpenseDto.class);
            expenseDto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return expenseDto;
    }

    @Override
    public ExpenseDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public ExpenseDto deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
