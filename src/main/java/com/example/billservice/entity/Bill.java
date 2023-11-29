package com.example.billservice.entity;

import com.example.billservice.dto.ResponseOrderItems;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


@Document(collection = "order-bills")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Bill implements Serializable {

    @Id
    @JsonProperty("billId")
    private String id;

    private String userName;

    @JsonProperty("order")
     private Order order;

    @JsonProperty("orderItems")
     private List<ResponseOrderItems> orderItems;
}
