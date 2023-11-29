package com.example.billservice.service.impl;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.dto.ResponseOrderItems;
import com.example.billservice.entity.Bill;
import com.example.billservice.entity.OrderItems;
import com.example.billservice.repository.service.BillRepoService;
import com.example.billservice.service.BillService;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.StringOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


@Service
public class BillServiceImpl implements BillService {

  @Autowired
  private BillRepoService billRepoService;

@Autowired
private JavaMailSender javaMailSender;

    @Override
    public ResponseEntity<ResponseDTO> createBills(BillDto billDto) {


      Bill bill=new Bill();

      bill.setUserName(billDto.getUserName());

      List<ResponseOrderItems> orderItemsList =new ArrayList<>();
      bill.setOrder(billDto.getOrder());
      System.out.println("dffedgd"+billDto.getOrderItems());
      int k=0;

      if(billDto.getOrderItems()!=null) {
        for (OrderItems items : billDto.getOrderItems()) {
          ResponseOrderItems orderItems = new ResponseOrderItems();

          orderItems.setItem(items.getItem().getName());
          orderItems.setQuantity(items.getQuantity());
          orderItems.setAmount(items.getAmount());
          orderItems.setGst(billDto.getGstList().get(k));
          k++;

          orderItemsList.add(orderItems);
        }
      }
      bill.setOrder(bill.getOrder());
      bill.setOrderItems(orderItemsList);
//      Gson  gson=new Gson();

//      Bill bill=gson.fromJson(jsonObject.toString(),Bill.class);
//      System.out.println(bill);
//      System.out.println(bill.getBillId());
//      bill.getOrderItems().get(0);
//      System.out.println("dfdsfsd");
      billRepoService.insert(bill);
    String id=bill.getId();

    generateMail(billDto.getEmailId(),bill,id);
      System.out.println(id);

    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("bill generated",id,HttpStatus.OK));
    }

  public void generateMail(String emailId,Bill bill,String id)
  {
    SimpleMailMessage message=new SimpleMailMessage();

    message.setFrom("rameshkrishnaprasath@gmail.com");
    message.setTo(emailId);

    Formatter formatter=new Formatter();
    String result="";String uid="customerId :"+bill.getUserName()+"\n";
     String bid="billId :"+id+"\n";
    formatter.format("%15s %15s %15s %15s\n","name","price","quantity","Gst");

    System.out.println(bill.getOrderItems());
    for(ResponseOrderItems items :bill.getOrderItems())
    {
      int space2=20-items.getAmount().toString().length();
      int space1=18-items.getItem().length();
      int space3=15- items.getQuantity().toString().length();
      int space4=23-items.getGst().toString().length();
      formatter.format("%"+space1+"s %"+(space2)+"s %"+space3+"s  %"+(space4-3)+"s\n ",items.getItem(),items.getAmount(),items.getQuantity(),items.getGst().toString().substring(0,4)+"%");
      result+=formatter;
    }

    System.out.println(formatter.toString());
    message.setText(uid+bid+formatter+
            bill.getOrder().getAmount());
    javaMailSender.send(message);
  }
}
