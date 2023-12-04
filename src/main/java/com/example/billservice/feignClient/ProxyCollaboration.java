package com.example.billservice.feignClient;


import com.example.billservice.dto.GenerateMailDTO;
import com.example.billservice.entity.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "PROXY-SERVER",url = "http://localhost:8082")

public interface ProxyCollaboration {

    @Async
    @PostMapping("Proxy/mailGeneration")
    void generateMail(@RequestBody GenerateMailDTO generateMailDTO);

}
