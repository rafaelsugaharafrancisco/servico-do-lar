package com.br.servicodolar.orderofservice.client;

import com.br.servicodolar.orderofservice.client.model.ServiceDTO;

import java.util.Optional;

public interface ServiceAPI {

    boolean serviceExists(long id);

    Optional<ServiceDTO> getOneService(long id);
}
