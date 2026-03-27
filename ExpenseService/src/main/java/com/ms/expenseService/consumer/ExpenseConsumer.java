package com.ms.expenseService.consumer;

import com.ms.expenseService.dto.ExpenseDto;
import com.ms.expenseService.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseConsumer {

    private ExpenseService expenseService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto expenseDto)
    {
        try{

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
