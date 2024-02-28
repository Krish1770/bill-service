package com.example.billservice.feignClient;


import com.example.billservice.dto.GenerateMailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PROXY-SERVER",url = "http://localhost:8082")

public interface ProxyCollaboration {

    @Async
    @PostMapping("Proxy/mailGeneration")
    void generateMail(@RequestBody GenerateMailDTO generateMailDTO);

}
