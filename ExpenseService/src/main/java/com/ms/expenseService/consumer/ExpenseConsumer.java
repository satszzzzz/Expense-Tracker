package com.ms.expenseService.consumer;

import com.ms.expenseService.dto.ExpenseDto;
import com.ms.expenseService.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@EnableKafka
public class ExpenseConsumer {

    private final ExpenseService expenseService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto expenseDto)
    {
        System.out.println("Consumer received: "+expenseDto.toString());
        try{
            expenseService.createExpense(expenseDto);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
