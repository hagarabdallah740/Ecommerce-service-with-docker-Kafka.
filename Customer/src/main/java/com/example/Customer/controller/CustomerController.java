package com.example.Customer.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Customer.Domain.Offer;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import com.example.Customer.service.CustomerService;
@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PutMapping("/update/{id}")
    public String update(@RequestBody Offer offer, @PathVariable Long id) {
        log.info("update offer request received");
        return customerService.update(offer, id) + " Offer(s) updated successfully";
    }
    @DeleteMapping("/deleteOffer")
    public String deleteOffer(@RequestBody Offer offer)
    {
        log.info("delete offer request received");
        return customerService.deleteOffer(offer);
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        log.info("delete offer  by id request received");
        customerService.deleteById(id);
        return " offer(s) with id: " + id + " deleted from the database";
    }
    @GetMapping("/offers/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable("id")long id){
        log.info("get offer by id request received");
        Offer offer = customerService.getOffer(id);
        if (offer !=null){
            return new ResponseEntity<>(offer, OK);
        }
        else{
            return new ResponseEntity<>(NOT_FOUND);
        }
    }
    @GetMapping("/getAllOffers")
    public ResponseEntity<List<Offer>> getAllOffers(){
        log.info("get all offers request received");
        return new ResponseEntity<>(customerService.getAllOffer(), OK);
    }

}
