package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.client.ServiceProviderAPI;
import com.br.servicodolar.orderofservice.domain.entity.Order;
import com.br.servicodolar.orderofservice.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

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
