package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public class ValidationInDBForServiceProvider implements ValidationInDB {

    private List<ServiceRequest> serviceRequestList;

    public ValidationInDBForServiceProvider(List<ServiceRequest> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }

    @Override
    public void validateIfServiceExistInDataBase(ServiceRequest newServiceRequest) {
        Optional<ServiceRequest> order = this.serviceRequestList.stream().filter(
                o -> (o.getServiceProviderId() == newServiceRequest.getServiceProviderId() &&
                        o.getServiceId() == newServiceRequest.getServiceId()) &&
                        o.getOpeningDate().isEqual(newServiceRequest.getOpeningDate())).findFirst();

        if (order.isPresent())
            throw new RuntimeException("Já existe um pedido desse serviço para esse Prestador.");
    }

    @Override
    public void validateIfDateTimeOfServiceExistInDB(ServiceRequest newServiceRequest, ValidationSchedule validationSchedule) {
        List<Schedule> scheduleList = this.serviceRequestList.stream()
                .filter(o -> o.getServiceProviderId() == newServiceRequest.getServiceProviderId())
                .map(order -> order.getSchedule()).toList();

        validationSchedule.validateIfDateAndTimeExist(scheduleList, newServiceRequest.getSchedule());
    }
}
