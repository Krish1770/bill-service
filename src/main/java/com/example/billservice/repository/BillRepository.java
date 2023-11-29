package com.example.billservice.repository;

import com.example.billservice.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill,String> {
}
