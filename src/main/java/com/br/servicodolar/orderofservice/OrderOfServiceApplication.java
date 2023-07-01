package com.br.servicodolar.orderofservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderOfServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderOfServiceApplication.class, args);
	}

}
