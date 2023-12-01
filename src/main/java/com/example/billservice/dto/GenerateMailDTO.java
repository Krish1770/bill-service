package com.example.billservice.dto;

import com.example.billservice.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GenerateMailDTO {

    private String emailId;

    private Bill bill;

    private String id;
}
