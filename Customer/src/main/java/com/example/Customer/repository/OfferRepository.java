package com.example.Customer.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Customer.Domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {

}
