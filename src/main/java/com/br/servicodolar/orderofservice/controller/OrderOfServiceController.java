package com.br.servicodolar.orderofservice.controller;

import com.br.servicodolar.orderofservice.usecase.InsertOrderOfService;
import com.br.servicodolar.orderofservice.usecase.model.OrderOfServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders-of-service")
public class OrderOfServiceController {

    private InsertOrderOfService insertOrderOfService;

    public OrderOfServiceController(InsertOrderOfService insertOrderOfService) {
        this.insertOrderOfService = insertOrderOfService;
    }

    @PostMapping
    public ResponseEntity registryOrder(OrderOfServiceDTO body, UriComponentsBuilder builder) {
        var order = this.insertOrderOfService.execute(body);

        URI uri = builder.path("/order-of-service/{id}").buildAndExpand(order.getServiceId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
