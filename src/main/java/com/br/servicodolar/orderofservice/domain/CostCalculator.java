package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.client.ServiceAPI;

public class CostCalculator implements Calculator {

    @Override
    public double execute(ServiceAPI serviceAPI, long id) {
        var serviceDTO = serviceAPI.getOneService(id)
                .orElseThrow(() -> new RuntimeException("Serviço inexistente."));

        return serviceDTO.amountOfHours() * 200.0;
    }
}
