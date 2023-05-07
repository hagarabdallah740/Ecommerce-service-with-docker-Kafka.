package com.Marketer.Domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Setter
@Getter
public class Offer {
    private Long checkId;
    private String item;
    private Double amount;
    private String endDate;
    private boolean deleted;
    private boolean updated;
}
