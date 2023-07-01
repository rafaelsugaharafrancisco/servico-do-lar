package com.br.servicodolar.orderofservice.repository;

import com.br.servicodolar.orderofservice.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByYearAndCostumerId(int year, long costumerId);

    List<Order> findAllByYearAndServiceProviderId(Integer year, Long serviceProviderId);

    Order save(Order order);
}
