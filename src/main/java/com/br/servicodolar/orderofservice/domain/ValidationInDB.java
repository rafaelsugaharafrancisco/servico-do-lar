package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.domain.entity.Order;

public interface ValidationInDB {

    void validateIfServiceExistInDataBase(Order newOrder);

    void validateIfDateTimeOfServiceExistInDB(Order newOrder, ValidationSchedule validationSchedule);

}
