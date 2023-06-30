package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.client.ServiceAPI;

public class ValidationInAPIForService implements ValidationInAPI {

    private ServiceAPI ServiceAPI;

    public ValidationInAPIForService(ServiceAPI ServiceAPI) {
        this.ServiceAPI = ServiceAPI;
    }

    @Override
    public void validateIfExistWithAPI(long id) {
        boolean exist = this.ServiceAPI.serviceExists(id);

        if (!exist)
            throw new RuntimeException("Serviço não existe");
    }
}
