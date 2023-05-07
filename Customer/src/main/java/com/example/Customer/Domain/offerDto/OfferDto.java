package com.example.Customer.Domain.offerDto;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class OfferDto {
    String item;
    Double amount;
    String endDate;
    public boolean deleted;
    public boolean updated;
    private Long checkId;
}