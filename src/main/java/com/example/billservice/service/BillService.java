package com.example.billservice.service;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.entity.Bill;
import jakarta.mail.MessagingException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface BillService {

    ResponseEntity<ResponseDTO> createBills(BillDto billDto) throws MessagingException;

  }
