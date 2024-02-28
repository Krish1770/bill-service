package com.example.billservice.controller;


import com.example.billservice.api.BillApi;
import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.BillResponseDto;
import com.example.billservice.service.BillService;
import com.example.billservice.service.impl.BillServiceImpl;
import jakarta.mail.MessagingException;
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
    public ResponseEntity<BillResponseDto> createBills(BillDto billDto) throws MessagingException {

        ResponseEntity<BillResponseDto>response = billService.createBills(billDto);

        System.out.println("value :"+response);
    return  response;
    }

    @Override
    public String health() {
        return "health";
    }

//    @Override
//    public void generateMailFromProxy(String emailId, Bill bill, String id) throws MessagingException {
//         billService.generateMailFromProxy(emailId,bill,id);
//    }

//    @Override
//    public void generateMail(String emailId, Bill bill, String id) throws MessagingException {
//     billService.generateMail(emailId,bill,id);
//    }

}

