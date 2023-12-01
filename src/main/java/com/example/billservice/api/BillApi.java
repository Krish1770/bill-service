package com.example.billservice.api;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.entity.Bill;
import jakarta.mail.MessagingException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("Bill")
public interface BillApi {

//    @PostMapping
//    void createBills(@RequestBody Bill bill);

    @PostMapping
     ResponseEntity<ResponseDTO>createBills(@RequestBody BillDto billDto) throws MessagingException;

//    @PostMapping("generateMailFromProxy")
//    void generateMailFromProxy(String emailId,Bill bill,String id) throws MessagingException;

//    @PostMapping("generateMail")
//    void generateMail(String emailId,Bill bill,String id) throws MessagingException;

}
