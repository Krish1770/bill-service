package com.example.billservice.service;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.entity.Bill;
import jakarta.mail.MessagingException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    ResponseEntity<ResponseDTO> createBills(BillDto billDto) throws MessagingException;

  }
