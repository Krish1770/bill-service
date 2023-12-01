package com.example.billservice.feignClient;


import com.example.billservice.entity.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "PROXY-SERVER",url = "http://localhost:8082")

public interface ProxyCollaboration {

    @PostMapping("Proxy/mailGeneration")
    void generateMail(String emailId, Bill bill,String id);

}
