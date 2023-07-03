package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;

public interface ValidationInDB {

    void validateIfServiceExistInDataBase(ServiceRequest newServiceRequest);

    void validateIfDateTimeOfServiceExistInDB(ServiceRequest newServiceRequest, ValidationSchedule validationSchedule);

}
