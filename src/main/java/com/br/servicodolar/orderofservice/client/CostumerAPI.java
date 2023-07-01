package com.br.servicodolar.orderofservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "costumer", url = "http://localhost:8000/costumers")
public interface CostumerAPI {

    @RequestMapping(method = RequestMethod.GET)
    boolean activeCostumerExists(@PathVariable long id);
}
