package com.br.servicodolar.orderofservice;


import com.br.servicodolar.orderofservice.client.CostumerAPI;
import com.br.servicodolar.orderofservice.client.ServiceAPI;
import com.br.servicodolar.orderofservice.client.ServiceProviderAPI;
import com.br.servicodolar.orderofservice.client.model.ServiceDTO;
import com.br.servicodolar.orderofservice.domain.ValidationInDBForCostumer;
import com.br.servicodolar.orderofservice.domain.ValidationInDBForServiceProvider;
import com.br.servicodolar.orderofservice.domain.ValidationScheduleInDB;
import com.br.servicodolar.orderofservice.domain.entity.Order;
import com.br.servicodolar.orderofservice.domain.entity.Schedule;
import com.br.servicodolar.orderofservice.domain.entity.StatusOrder;
import com.br.servicodolar.orderofservice.repository.OrderRepository;
import com.br.servicodolar.orderofservice.usecase.InsertOrderOfService;
import com.br.servicodolar.orderofservice.usecase.model.OrderOfServiceDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class InsertOrderOfServiceTest {

    @MockBean
    private CostumerAPI costumerAPI;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ServiceAPI serviceAPI;

    @MockBean
    private ServiceProviderAPI serviceProviderAPI;

    @Test
    void test() {

        var dto = new OrderOfServiceDTO(1l,
                2l,
                3l,
                StatusOrder.ABERTO,
                LocalDate.now(),
                LocalTime.of(8,0),
                LocalDate.now(),
                LocalTime.of(9,0));

        Mockito.when(orderRepository.findAllByYearAndCostumerId(2023, 1l)).thenReturn(Collections.emptyList());
        Mockito.when(orderRepository.findAllByYearAndServiceProviderId(2023, 2l)).thenReturn(Collections.emptyList());
        Mockito.when(costumerAPI.activeCostumerExists(1l)).thenReturn(true);
        Mockito.when(serviceProviderAPI.activeServiceProviderExists(2l)).thenReturn(true);
        Mockito.when(serviceAPI.serviceExists(3l)).thenReturn(true);
        Mockito.when(serviceAPI.getOneService(3l)).thenReturn(Optional.of(new ServiceDTO(1l, "Trocar torneira", 1.5)));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(new Order());

        new InsertOrderOfService(costumerAPI, orderRepository, serviceAPI, serviceProviderAPI).execute(dto);
        Mockito.verify(orderRepository).findAllByYearAndCostumerId(Mockito.anyInt(), Mockito.anyLong());
        Mockito.verify(orderRepository).findAllByYearAndServiceProviderId(Mockito.anyInt(), Mockito.anyLong());
        Mockito.verify(costumerAPI).activeCostumerExists(Mockito.anyLong());
        Mockito.verify(serviceAPI).serviceExists(Mockito.anyLong());
        Mockito.verify(serviceProviderAPI).activeServiceProviderExists(Mockito.anyLong());
        Mockito.verify(orderRepository).save(Mockito.any(Order.class));
    }

}
