package com.example.billservice.api;

import com.example.billservice.utils.ApiConstants;
import com.example.billservice.dto.BillDto;

import com.example.billservice.dto.BillResponseDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@Async
@RequestMapping(value = ApiConstants.createBills)

//@RequestMapping(value = "Bill")
public interface BillApi {

//    @PostMapping
//    void createBills(@RequestBody Bill bill);

    @PostMapping()
   ResponseEntity<BillResponseDto>createBills(@RequestBody BillDto billDto) throws MessagingException;

    @PostMapping(value = "/health")
    String health();
//    @PostMapping("generateMailFromProxy")
//    void generateMailFromProxy(String emailId,Bill bill,String id) throws MessagingException;

//    @PostMapping("generateMail")
//    void generateMail(String emailId,Bill bill,String id) throws MessagingException;

}
