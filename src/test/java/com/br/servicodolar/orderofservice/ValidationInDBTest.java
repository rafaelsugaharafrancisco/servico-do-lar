package com.br.servicodolar.orderofservice;


import com.br.servicodolar.orderofservice.domain.ValidationInDBForCostumer;
import com.br.servicodolar.orderofservice.domain.ValidationInDBForServiceProvider;
import com.br.servicodolar.orderofservice.domain.ValidationScheduleInDB;
import com.br.servicodolar.orderofservice.domain.entity.Order;
import com.br.servicodolar.orderofservice.domain.entity.Schedule;
import com.br.servicodolar.orderofservice.domain.entity.StatusOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class ValidationInDBTest {

    private List<Order> orderList;

    @BeforeEach
    void createScheduleList() {

        var order = new Order();
        order.setCostumerId(1L);
        order.setServiceProviderId(2L);
        order.setServiceId(3L);
        order.setStatusOrder(StatusOrder.ABERTO);
        order.setYear(2023);
        order.setOpeningDate(LocalDate.of(2023, 6, 26));
        order.setTotalServiceCost(1000.20);
        order.setSchedule(new Schedule(LocalDate.now(), LocalTime.of(8,0), LocalDate.now(), LocalTime.of(9,0)));
        order.setUpdatedDateTime(LocalDateTime.now());

        this.orderList = List.of(order);
    }

    @Test
    void shouldThrowExceptionWhenSameServiceAlreadyExists() {

        Order newOrder = new Order();
        newOrder.setCostumerId(1L);
        newOrder.setServiceProviderId(2L);
        newOrder.setServiceId(3L);
        newOrder.setStatusOrder(StatusOrder.ABERTO);
        newOrder.setYear(2023);
        newOrder.setOpeningDate(LocalDate.of(2023, 6, 26));
        newOrder.setTotalServiceCost(1000.20);
        newOrder.setSchedule(new Schedule(LocalDate.now(), LocalTime.of(8,0), LocalDate.now(), LocalTime.of(9,0)));
        newOrder.setUpdatedDateTime(LocalDateTime.now());

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForCostumer(this.orderList).validateIfServiceExistInDataBase(newOrder));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForCostumer(this.orderList).validateIfDateTimeOfServiceExistInDB(newOrder, new ValidationScheduleInDB()));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForServiceProvider(this.orderList).validateIfServiceExistInDataBase(newOrder));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForServiceProvider(this.orderList).validateIfDateTimeOfServiceExistInDB(newOrder, new ValidationScheduleInDB()));



    }

}
