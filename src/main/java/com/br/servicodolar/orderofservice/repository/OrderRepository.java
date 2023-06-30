package com.br.servicodolar.orderofservice.repository;

import com.br.servicodolar.orderofservice.domain.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAllByYearAndCostumerId(int year, long costumerId);

    List<Order> findAllByYearAndServiceProviderId(Integer year, Long serviceProviderId);

    Order save(Order order);
}
