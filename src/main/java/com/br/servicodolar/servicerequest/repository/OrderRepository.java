package com.br.servicodolar.servicerequest.repository;

import com.br.servicodolar.servicerequest.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByYearAndCostumerId(Integer year, Long costumerId);

    List<Order> findAllByYearAndServiceProviderId(Integer year, Long serviceProviderId);

    Order save(Order order);
}
