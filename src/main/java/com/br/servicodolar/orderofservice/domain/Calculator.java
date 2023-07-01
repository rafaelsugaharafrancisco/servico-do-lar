package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.client.ServiceAPI;

public interface Calculator {

    double execute(ServiceAPI serviceAPI, long id);
}
