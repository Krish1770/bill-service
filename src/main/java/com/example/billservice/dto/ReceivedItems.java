package com.example.billservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivedItems implements Serializable {
    private Long itemId;

    private String name;
    private Long price;

    private String desc;


    @Data
    public static class Category {

        private Long typeId;
        private String name;
        private BigDecimal gst;
    }
}