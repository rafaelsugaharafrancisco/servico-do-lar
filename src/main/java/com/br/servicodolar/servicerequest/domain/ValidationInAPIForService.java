package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.client.ServiceAPI;

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
