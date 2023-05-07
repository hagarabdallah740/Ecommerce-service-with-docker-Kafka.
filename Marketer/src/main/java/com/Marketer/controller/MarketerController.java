package com.Marketer.controller;

import com.Marketer.service.MarketerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import  com.Marketer.Domain.Offer;

import static org.springframework.http.HttpStatus.*;
@Slf4j
@RestController
@RequestMapping("/marketer")
public class MarketerController {
    private final MarketerService marketerService;
    @Autowired
    public MarketerController(MarketerService marketerService){
        this.marketerService = marketerService;
    }
    @PostMapping("/publishOffer")
    public String publishOffer(@RequestBody Offer offer)throws JsonProcessingException{
        log.info("publish offer request received");
        return marketerService.sendOffer(offer);

    }
    @DeleteMapping("/DeleteOffer")
    public String deleteOffer(@RequestBody Offer offer)throws JsonProcessingException{
        log.info("delete offer request received");
        offer.setDeleted(true);
        return marketerService.sendOffer(offer);

    }
    @PutMapping("/UpdateOffer")
    public String updateOffer(@RequestBody Offer offer)throws JsonProcessingException{
        log.info("update offer request received");
        offer.setUpdated(true);
        return marketerService.sendOffer(offer);

    }
}
