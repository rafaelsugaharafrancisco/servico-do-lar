package com.br.servicodolar.servicerequest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "serviceProvider", url = "http://localhost:8000/service-providers")
public interface ServiceProviderAPI {

    @RequestMapping(method = RequestMethod.GET)
    boolean activeServiceProviderExists(@PathVariable long id);
}
