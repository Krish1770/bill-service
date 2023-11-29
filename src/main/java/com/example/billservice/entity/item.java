package com.example.billservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class item implements Serializable {

   @JsonProperty("name")
    private String name;

}
