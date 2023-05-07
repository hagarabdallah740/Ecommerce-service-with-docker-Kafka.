package com.example.Customer.service.consumer;
import com.example.Customer.Domain.offerDto.OfferDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.Customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
@Slf4j
@Component
public class Consumer {
    private static final String offerTopic = "${topic.name}";
    private final ObjectMapper objectMapper;
    private final CustomerService customerService;
    @Autowired
    public Consumer (ObjectMapper objectMapper,CustomerService customerService){
        this.customerService = customerService;
        this.objectMapper = objectMapper;
    }
    @KafkaListener(topics = offerTopic)
    public void customerMessage(String message) throws JsonProcessingException{
        log.info("message consumed {}",message);
        OfferDto offerDto = objectMapper.readValue(message,OfferDto.class);
        if (offerDto.deleted){
            customerService.deleteOffer_producer(offerDto);
        } else if (offerDto.updated) {
            customerService.updateByProducer(offerDto);
        } else{
            customerService.persistOffer(offerDto);}
    }
}
