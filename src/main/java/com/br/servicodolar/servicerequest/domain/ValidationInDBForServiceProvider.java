package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.domain.entity.Order;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public class ValidationInDBForServiceProvider implements ValidationInDB {

    private List<Order> orderList;

    public ValidationInDBForServiceProvider(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public void validateIfServiceExistInDataBase(Order newOrder) {
        Optional<Order> order = this.orderList.stream().filter(
                o -> (o.getServiceProviderId() == newOrder.getServiceProviderId() &&
                        o.getServiceId() == newOrder.getServiceId()) &&
                        o.getOpeningDate().isEqual(newOrder.getOpeningDate())).findFirst();

        if (order.isPresent())
            throw new RuntimeException("Já existe um pedido desse serviço para esse Prestador.");
    }

    @Override
    public void validateIfDateTimeOfServiceExistInDB(Order newOrder, ValidationSchedule validationSchedule) {
        List<Schedule> scheduleList = this.orderList.stream()
                .filter(o -> o.getServiceProviderId() == newOrder.getServiceProviderId())
                .map(order -> order.getSchedule()).toList();

        validationSchedule.validateIfDateAndTimeExist(scheduleList, newOrder.getSchedule());
    }
}
