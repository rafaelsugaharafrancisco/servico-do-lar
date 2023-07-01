package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.domain.entity.Order;

public interface ValidationInDB {

    void validateIfServiceExistInDataBase(Order newOrder);

    void validateIfDateTimeOfServiceExistInDB(Order newOrder, ValidationSchedule validationSchedule);

}
