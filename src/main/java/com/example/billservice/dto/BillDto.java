package com.example.billservice.dto;

import com.example.billservice.entity.Order;
import com.example.billservice.entity.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto implements Serializable {



        private String userName;

        private Order order;

        private List<OrderItems> orderItems;

        private List<BigDecimal> gstList;

        private String emailId;

}
