package com.example.billservice.repository.service;

import com.example.billservice.entity.Bill;
import org.springframework.stereotype.Service;

@Service
public interface BillRepoService {
    void insert(Bill bill);
}
