package com.example.billservice.repository.service.impl;


import com.example.billservice.entity.Bill;
import com.example.billservice.repository.BillRepository;
import com.example.billservice.repository.service.BillRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillRepoServiceImpl implements BillRepoService {

    @Autowired
    private BillRepository billRepository;
    @Override
    public void insert(Bill bill) {
     billRepository.insert(bill);
    }
}
