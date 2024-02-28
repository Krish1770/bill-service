package com.example.billservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.Model;


@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class BillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillServiceApplication.class, args);
	}


	@Bean
	public ModelMapper  modelMapper()
	{
		return new ModelMapper();
	}
}
