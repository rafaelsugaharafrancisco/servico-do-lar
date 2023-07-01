package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.client.ServiceAPI;

public class CostCalculator implements Calculator {

    @Override
    public double execute(ServiceAPI serviceAPI, long id) {
        var serviceDTO = serviceAPI.getOneService(id)
                .orElseThrow(() -> new RuntimeException("Serviço inexistente."));

        return serviceDTO.amountOfHours() * 200.0;
    }
}
