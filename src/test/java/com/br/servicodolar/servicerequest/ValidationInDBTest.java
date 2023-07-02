package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.domain.ValidationInDBForCostumer;
import com.br.servicodolar.servicerequest.domain.ValidationInDBForServiceProvider;
import com.br.servicodolar.servicerequest.domain.ValidationScheduleInDB;
import com.br.servicodolar.servicerequest.domain.entity.Order;
import com.br.servicodolar.servicerequest.domain.entity.OrderBuilder;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.StatusOrder;
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

        var orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        this.orderList = List.of(orderBuilder.createOrder());
    }

    @Test
    void shouldThrowExceptionWhenSameServiceAlreadyExists() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        Order newOrder = orderBuilder.createOrder();

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
