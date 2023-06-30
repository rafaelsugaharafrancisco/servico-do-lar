package com.br.servicodolar.orderofservice;

import com.br.servicodolar.orderofservice.domain.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest {


    @Test
    void shouldThrowExceptionWhenCostumerNotFounded() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setCostumerId(-1L));
    }

    @Test
    void shouldThrowExceptionWhenServiceProviderNotFounded() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setServiceProviderId(-1L));
    }

    @Test
    void shouldThrowExceptionWhenServiceNotFounded() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setServiceId(-1L));
    }

    @Test
    void shouldThrowExceptionWhenStatusOrderIsNull() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setStatusOrder(null));
    }

    @Test
    void shouldThrowExceptionWhenYearEqualZero() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setYear(0));
    }

    @Test
    void shouldThrowExceptionWhenOpeningDateIsNull() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setOpeningDate(null));
    }

    @Test
    void shouldThrowExceptionWhenTotalCostEqualZero() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setTotalServiceCost(0.0));
    }

    @Test
    void shouldThrowExceptionWhenScheduleIsNull() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setSchedule(null));
    }

    @Test
    void shouldThrowExceptionWhenUpdatedDateTimeIsNull() {

        var newOrder = new Order();

        Assertions.assertThrows(RuntimeException.class,
                () -> newOrder.setUpdatedDateTime(null));
    }
}
