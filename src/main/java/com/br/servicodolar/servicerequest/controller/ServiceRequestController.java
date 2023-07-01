package com.br.servicodolar.servicerequest.controller;

import com.br.servicodolar.servicerequest.usecase.InsertServiceRequest;
import com.br.servicodolar.servicerequest.usecase.model.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/service-request")
public class ServiceRequestController {

    private InsertServiceRequest insertServiceRequest;

    public ServiceRequestController(InsertServiceRequest insertServiceRequest) {
        this.insertServiceRequest = insertServiceRequest;
    }

    @PostMapping
    public ResponseEntity registryRequest(@RequestBody OrderDTO body, UriComponentsBuilder builder) {
        var order = this.insertServiceRequest.execute(body);

        URI uri = builder.path("/service-request/{id}").buildAndExpand(order.getServiceId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
