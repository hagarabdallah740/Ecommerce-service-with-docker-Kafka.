package com.Marketer.service.producer;

import com.Marketer.Domain.Offer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservation;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producer {

    @Value("${topic.name}")
    private String offerTopic;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String,String> kafkaTemplate , ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;

    }

    public String sendMessage(Offer offer)throws JsonProcessingException{
        String offerAsMessage = objectMapper.writeValueAsString(offer);
        kafkaTemplate.send(offerTopic , offerAsMessage);
        log.info("offer produced {}",offerAsMessage);
        return "offer sent";
    }
}
