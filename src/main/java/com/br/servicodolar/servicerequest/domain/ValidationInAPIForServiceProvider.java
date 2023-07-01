package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.client.ServiceProviderAPI;

public class ValidationInAPIForServiceProvider implements ValidationInAPI {

    private ServiceProviderAPI ServiceProviderAPI;

    public ValidationInAPIForServiceProvider(ServiceProviderAPI ServiceProviderAPI) {
        this.ServiceProviderAPI = ServiceProviderAPI;
    }

    @Override
    public void validateIfExistWithAPI(long id) {
        boolean exist = this.ServiceProviderAPI.activeServiceProviderExists(id);

        if (!exist)
            throw new RuntimeException("Prestador não existe");
    }

}
