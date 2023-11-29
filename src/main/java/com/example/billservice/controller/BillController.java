package com.example.billservice.controller;


import com.example.billservice.api.BillApi;
import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.entity.Bill;
import com.example.billservice.service.BillService;
import com.example.billservice.service.impl.BillServiceImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController implements BillApi {


    @Autowired
    private BillServiceImpl billServiceImpl;
    @Autowired
    private BillService billService;

    @Override
    public ResponseEntity<ResponseDTO> createBills(BillDto billDto) {
    return  billService.createBills(billDto);
    }

}

