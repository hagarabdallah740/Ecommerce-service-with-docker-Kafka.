package com.example.Customer.service;
import com.example.Customer.Domain.Offer;
import com.example.Customer.Domain.offerDto.OfferDto;
import com.example.Customer.Domain.Offer;
import com.example.Customer.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
@Slf4j
public class CustomerService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public CustomerService(OfferRepository offerRepository,ModelMapper modelMapper){
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }
    public void persistOffer(OfferDto offerDto){
        Offer offer = modelMapper.map(offerDto, Offer.class);
        Offer persistedOffer = offerRepository.save(offer);
        log.info("offer persisted{}",persistedOffer);
    }
    public Offer getOffer(Long id){
        Optional<Offer> offer = offerRepository.findById(id);
        log.info("offer fetched{}",offer);
        return offer.orElse(null);
    }
    public List<Offer> getAllOffer(){
        List<Offer> offers = offerRepository.findAll();
        log.info("offers size{}",offers.size());
        return offers;
    }
    public void deleteOffer_producer(OfferDto offerDto){

        Offer offer = modelMapper.map(offerDto, Offer.class);
        offer.setId(offerDto.getCheckId());
        offer.setDeleted(false);
        offerRepository.delete(offer);
        log.info("offer deleted{}",offer);
    }
    public String  deleteOffer(Offer offerDto){
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offerRepository.delete(offer);
        return "offer"+offer+"deleted";
    }
    public String deleteById(Long id) {
        offerRepository.deleteById(id);
        return "offer deleted";
    }
    public Offer update(Offer updatedOffer, Long id) {
        Optional<Offer> oldOffer = offerRepository.findById(id);
        if (oldOffer.isPresent()) {
            Offer offer = oldOffer.get();
            offer.setId(id);
            offer.setItem(updatedOffer.getItem());
            offer.setAmount(updatedOffer.getAmount());
            offer.setEndDate(updatedOffer.getEndDate());
            return offerRepository.save(offer);
        } else{
            return null;}
    }

    public Offer updateByProducer(OfferDto updatedOffer) {
        Optional<Offer> oldOffer = offerRepository.findById(updatedOffer.getCheckId());
        if (oldOffer.isPresent()) {
            Offer offer = oldOffer.get();
            offer.setId(updatedOffer.getCheckId());
            offer.setItem(updatedOffer.getItem());
            offer.setAmount(updatedOffer.getAmount());
            offer.setEndDate(updatedOffer.getEndDate());
            offer.setUpdated(false);
            log.info("offer updated{}",offer);
            return offerRepository.save(offer);
        } else
            return null;
    }

}
