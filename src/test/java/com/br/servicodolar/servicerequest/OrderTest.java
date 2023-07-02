package com.br.servicodolar.servicerequest;

import com.br.servicodolar.servicerequest.domain.entity.Order;
import com.br.servicodolar.servicerequest.domain.entity.OrderBuilder;
import com.br.servicodolar.servicerequest.domain.entity.StatusOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrderTest {


    @Test
    void shouldThrowExceptionWhenCostumerIdIsNegative() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(-1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        var message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do cliente não pode ser menor igual a zero.", message);
//        Assertions.assertEquals("O código do cliente tem que ser positivo.", message);

    }

    @Test
    void shouldThrowExceptionWhenCostumerIdIsNull() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(null)
                .setServiceProviderId(2l)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(1000.20)
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        var message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do cliente não pode ser nulo.", message);

    }

    @Test
    void shouldThrowExceptionWhenServiceProviderIdIsNegative() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(-2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do prestador não pode ser menor ou igual a zero.", message);
    }

    @Test
    void shouldThrowExceptionWhenServiceProviderIsNull() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(null)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(1000.20)
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do prestador não pode ser nulo.", message);
    }
    @Test
    void shouldThrowExceptionWhenServiceIdIsNegative() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(-3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do serviço não pode ser menor ou igual a zero.", message);
    }

    @Test
    void shouldThrowExceptionWhenServiceIdIsNull() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(null)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(1000.20)
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Código do serviço não pode ser nulo.", message);
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
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("Status não pode ser nulo.", message);
    }

    @Test
    void shouldThrowExceptionWhenYearIsNegative() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                    .setServiceProviderId(2L)
                    .setServiceId(3L)
                    .setStatusOrder(StatusOrder.ABERTO)
                    .setYear(-2023)
                    .setOpeningDate(LocalDate.of(2023, 6, 26))
                    .setTotalServiceCost(1000.20)
                    .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                    .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("O valor de ano tem que ser maior igual a zero.", message);
    }

    @Test
    void shouldThrowExceptionWhenYearIsNull() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(null)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(1000.20)
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("O ano não pode ser nulo.", message);
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
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("A data de abertura não pode ser nulo.", message);
    }

    @Test
    void shouldThrowExceptionWhenTotalCostIsNegative() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(-1000.20)
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("O valor total tem que ser maior igual a zero.", message);


    }

    @Test
    void shouldThrowExceptionWhenTotalCostIsNull() {

        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCostumerId(1L)
                .setServiceProviderId(2L)
                .setServiceId(3L)
                .setStatusOrder(StatusOrder.ABERTO)
                .setYear(2023)
                .setOpeningDate(LocalDate.of(2023, 6, 26))
                .setTotalServiceCost(-1000.20)
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(LocalDateTime.now());

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("O valor total tem que ser maior igual a zero.", message);
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
                .setSchedule(LocalDate.of(2023, 6, 26), LocalTime.of(8,0), LocalDate.of(2023, 6, 26), LocalTime.of(9,0))
                .setUpdatedDateTime(null);

        String message = Assertions.assertThrows(RuntimeException.class, () -> orderBuilder.createOrder()).getMessage();
        Assertions.assertEquals("A data de atualização não pode ser nulo.", message);

    }
}
