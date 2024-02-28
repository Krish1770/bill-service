package com.example.billservice.service.impl;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.BillResponseDto;
import com.example.billservice.dto.GenerateMailDTO;
import com.example.billservice.dto.ResponseOrderItems;
import com.example.billservice.entity.Bill;
import com.example.billservice.entity.OrderItems;
import com.example.billservice.feignClient.ProxyCollaboration;
import com.example.billservice.repository.service.BillRepoService;
import com.example.billservice.service.BillService;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private ProxyCollaboration proxyCollaboration;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BillRepoService billRepoService;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public ResponseEntity<BillResponseDto> createBills(BillDto billDto) throws MessagingException {


        Bill bill = new Bill();
        System.out.println(billDto);
        bill.setUserName(billDto.getUserName());

        List<ResponseOrderItems> orderItemsList = new ArrayList<>();
        bill.setOrder(billDto.getOrder());
        System.out.println("dffedgd" + billDto.getOrderItems());
        int k = 0;

        if (billDto.getOrderItems() != null) {
            for (OrderItems items : billDto.getOrderItems()) {
                ResponseOrderItems orderItems = new ResponseOrderItems();

                orderItems = modelMapper.map(items, ResponseOrderItems.class);
                orderItems.setItem(items.getItem().getName());
                orderItems.setGst(billDto.getGstList().get(k));
                k++;

                orderItemsList.add(orderItems);
            }
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BillResponseDto("orderItem is null", "", HttpStatus.NOT_FOUND));

        bill.setOrder(bill.getOrder());
        bill.setOrderItems(orderItemsList);

        billRepoService.insert(bill);
        GenerateMailDTO generateMailDTO = new GenerateMailDTO(billDto.getEmailId(), bill, bill.getId());
        System.out.println(generateMailDTO);
        proxyCollaboration.generateMail(generateMailDTO);

        return (ResponseEntity.status(HttpStatus.OK).body(new BillResponseDto("bill generated", bill.getId(), HttpStatus.OK)));
    }

}
