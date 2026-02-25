//package org.example.eventProducer;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.example.entities.UserInfo;
//import org.example.model.UserInfoDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class UserInfoProducer {
//
//    @Autowired
//    private KafkaTemplate<String, UserInfo> kafkaTemplate;
//
//    @Value("${spring.kafka.topic.name}")
//    private String TOPIC_NAME;
//
//    public void sentToTopic(UserInfoDto userInfoDto)
//    {
//        Message<UserInfoDto> message = MessageBuilder.withPayload(userInfoDto)
//                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME).build();
//
//    }
//
//}


/*

[authService] --a--> Kafka (topics/etc) --b--> [UserService]
when you publish an event to kafka, it must be serialized (array of bytes like [1001010100101])
a -> data serialized to [10010001011010101]
b -> [10010001011010101] deserialized to data

data -> key - value i.e. String (key) - UserInfo (Value)

If value is string, it is automatically serialized. If any other object, you need to serialize
by extending the Serializer class

 */