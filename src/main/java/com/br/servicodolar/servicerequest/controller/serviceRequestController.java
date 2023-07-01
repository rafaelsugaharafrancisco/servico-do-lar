package com.br.servicodolar.servicerequest.controller;

import com.br.servicodolar.servicerequest.usecase.InsertOrderOfService;
import com.br.servicodolar.servicerequest.usecase.model.OrderOfServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/service-request")
public class serviceRequestController {

    private InsertOrderOfService insertOrderOfService;

    public serviceRequestController(InsertOrderOfService insertOrderOfService) {
        this.insertOrderOfService = insertOrderOfService;
    }

    @PostMapping
    public ResponseEntity registryOrder(OrderOfServiceDTO body, UriComponentsBuilder builder) {
        var order = this.insertOrderOfService.execute(body);

        URI uri = builder.path("/order-of-service/{id}").buildAndExpand(order.getServiceId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
