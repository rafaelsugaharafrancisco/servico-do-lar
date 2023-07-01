package com.br.servicodolar.servicerequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServiceRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRequestApplication.class, args);
	}

}
