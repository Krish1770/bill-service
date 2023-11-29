package com.example.billservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderItems {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("item")
    private String item;

    private BigDecimal gst;

    @JsonProperty("quantity")
    private Long quantity;

    @JsonProperty("amount")
    private Long amount;
}
