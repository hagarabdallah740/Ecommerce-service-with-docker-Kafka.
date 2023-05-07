package com.Marketer.service;

import com.Marketer.Domain.Offer;
import com.Marketer.service.producer.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MarketerService {
    private final Producer producer;
    @Autowired
    public MarketerService(Producer producer){
        this.producer = producer;
    }
    public String sendOffer(Offer offer)throws JsonProcessingException{
        return producer.sendMessage(offer);
    }
}
