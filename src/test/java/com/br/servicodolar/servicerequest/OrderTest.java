package com.br.servicodolar.servicerequest;

import com.br.servicodolar.servicerequest.domain.entity.OrderBuilder;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.StatusOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrderTest {


    @Test
    void shouldThrowExceptionWhenInvalidCostumer() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(-1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                    .setUpdatedDateTime(LocalDateTime.now());

        var message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do cliente inválido.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidServiceProvider() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(-2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Códido do prestador inválido.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidService() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(-3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do serviço inválido.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidStatusOrder() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(null)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Status não pode ser nulo.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidYear() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(-2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("O valor de ano tem que ser maior igual a zero.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidOpeningDate() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(null)
                .setTotalServiceCost(1000.20)
                .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("A data de abertura não pode ser nulo.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidTotalCost() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(-1000.20)
                .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("O valor total tem que ser maior igual a zero.", message);
    }

    @Test
    void shouldThrowExceptionWhenInvalidSchedule() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(1000.20)
                .setSchedule(null)
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("A agenda não pode ser nulo.", message);
    }

    @Test
    void shouldThrowExceptionWhenUpdatedDateTimeIsNull() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(1000.20)
                .setSchedule(new Schedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0)))
                .setUpdatedDateTime(null);

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("A data de atualização não pode ser nulo.", message);
    }
}
