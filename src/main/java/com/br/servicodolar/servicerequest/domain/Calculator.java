package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.client.ServiceAPI;

public interface Calculator {

    double execute(ServiceAPI serviceAPI, long id);
}
