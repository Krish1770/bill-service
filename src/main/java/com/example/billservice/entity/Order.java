package com.example.billservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {

    @JsonProperty("orderId")
    private String orderId;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("orderedDate")
    private Date orderedDate;


}
