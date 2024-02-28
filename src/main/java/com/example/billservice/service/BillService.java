package com.example.billservice.service;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.BillResponseDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BillService {

    ResponseEntity<BillResponseDto> createBills(BillDto billDto) throws MessagingException;

  }
