package com.br.servicodolar.orderofservice.client;

import com.br.servicodolar.orderofservice.client.model.ServiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "service", url = "http://localhost:8000/services")
public interface ServiceAPI {

    @RequestMapping(method = RequestMethod.GET)
    boolean serviceExists(@PathVariable long id);

    @RequestMapping(method = RequestMethod.GET)
    Optional<ServiceDTO> getOneService(@PathVariable long id);
}
