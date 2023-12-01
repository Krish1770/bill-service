package com.example.billservice.service.impl;

import com.example.billservice.dto.BillDto;
import com.example.billservice.dto.GenerateMailDTO;
import com.example.billservice.dto.ResponseDTO;
import com.example.billservice.dto.ResponseOrderItems;
import com.example.billservice.entity.Bill;
import com.example.billservice.entity.OrderItems;
import com.example.billservice.feignClient.ProxyCollaboration;
import com.example.billservice.repository.service.BillRepoService;
import com.example.billservice.service.BillService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private ProxyCollaboration proxyCollaboration;
    @Autowired
    private BillRepoService billRepoService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ResponseEntity<ResponseDTO> createBills(BillDto billDto) throws MessagingException {


        Bill bill = new Bill();

        bill.setUserName(billDto.getUserName());

        List<ResponseOrderItems> orderItemsList = new ArrayList<>();
        bill.setOrder(billDto.getOrder());
        System.out.println("dffedgd" + billDto.getOrderItems());
        int k = 0;

        if (billDto.getOrderItems() != null) {
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
        String id = bill.getId();

//        generateMail(billDto.getEmailId(), bill, id);

        System.out.println(id);

        GenerateMailDTO generateMailDTO=new GenerateMailDTO(billDto.getEmailId(),bill,id);

        proxyCollaboration.generateMail(generateMailDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("bill generated", id, HttpStatus.OK));
    }

//    @Override
//    public void generateMail(String emailId, Bill bill, String id) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
//
//
//        System.out.println("step3");
////    Formatter formatter=new Formatter();
////    String result="";String uid="customerId :"+bill.getUserName()+"\n";
////     String bid="billId :"+id+"\n";
////    formatter.format("%15s %15s %15s %15s\n","name","price","quantity","Gst");
////
////    System.out.println(bill.getOrderItems());
////    for(ResponseOrderItems items :bill.getOrderItems())
////    {
////      int space2=20-items.getAmount().toString().length();
////      int space1=18-items.getItem().length();
////      int space3=15- items.getQuantity().toString().length();
////      int space4=23-items.getGst().toString().length();
////      formatter.format("%"+space1+"s %"+(space2)+"s %"+space3+"s  %"+(space4-3)+"s\n ",items.getItem(),items.getAmount(),items.getQuantity(),items.getGst().toString().substring(0,4)+"%");
////      result+=formatter;
////    }
////
////    System.out.println(formatter.toString());
////    message.setText(uid+bid+formatter+
////            bill.getOrder().getAmount());
////    javaMailSender.send(message);
//
//        MimeMessage message1 = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message1, "utf-8");
//
//
//        StringBuilder str = new StringBuilder();
//
//        str.append("<html><body>"
//                + "<table style='border:2px solid black'>");
////
////    str.append("<tr>");
////    str.append("<th>name</th>");
////    str.append("<th>price</th>");
////    str.append("<th>quantity</th>");
////    str.append("<th>GST</>");
////    for(ResponseOrderItems items :bill.getOrderItems())
////    {
////      str.append("<tr>");
////      str.append("<td>");
////      str.append(items.getItem());
////      str.append("</td>");
////      str.append("<td>");
////      str.append(items.getAmount());
////      str.append("</td>");
////      str.append("<td>");
////      str.append(items.getQuantity());
////      str.append("</td>");
////      str.append("<td>");
////      str.append(items.getGst());
////      str.append("</td>");
////      str.append("</tr>");
////    }
////    str.append("</table></body></html>");
////
////    helper.setFrom("rameshkrishnaprasath@gmail.com");
////    helper.setTo(emailId);
////
////
////    System.out.println(str);
////    helper.setText(str.toString());
////    javaMailSender.send(message);
//
//        String[][] data = {{"1", "2"}, {"3", "4"}};
//        sendTableEmail(emailId, bill, id);
//    }
//
//
//
//    public void sendTableEmail(String to, Bill bill, String id) {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
//
//        try {
//            messageHelper.setTo(to);
//
//            String res = "name: " + bill.getUserName() + "\n" + "bill-Id :" + bill.getId();
//
//
//            res += buildHtmlTable(bill);
//            messageHelper.setSubject("your generated bill from our side");
//            messageHelper.setText(res, true);
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Failed to send email", e);
//        }
//    }
//
//    private String buildHtmlTable(Bill bill) {
//        StringBuilder str = new StringBuilder();
//        str.append("<html><body><table border='1'>");
//
//        double tot = 0;
//        str.append("<tr>");
//        str.append("<th>name</th>");
//        str.append("<th>price</th>");
//        str.append("<th>quantity</th>");
//        str.append("<th>GST</th>");
//        str.append("<th>Amount</th>");
//        for (ResponseOrderItems items : bill.getOrderItems()) {
//            str.append("<tr>");
//            str.append("<td>");
//            str.append(items.getItem());
//            str.append("</td>");
//            str.append("<td>");
//            str.append(items.getAmount());
//            str.append("</td>");
//            str.append("<td>");
//            str.append(items.getQuantity());
//            str.append("</td>");
//            str.append("<td>");
//            str.append(items.getGst().toString().substring(0, 5) + "%");
//            str.append("</td>");
//            str.append("<td>");
//            Double ert = (items.getAmount() * items.getQuantity()) + (double) (items.getAmount()) / 100 * items.getGst().doubleValue() * items.getQuantity();
//            str.append(ert);
//            tot += ert;
//            str.append("</td>");
//            str.append("</tr>");
//        }
//
//
//        str.append("</table></body></html>");
//
//        str.append("total :" + tot);
//        return str.toString();
//    }

}
