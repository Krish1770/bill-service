package com.example.billservice.api;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.entity.Bill;
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
    ResponseEntity<ResponseDTO> createBills(@RequestBody BillDto billDto);

}
