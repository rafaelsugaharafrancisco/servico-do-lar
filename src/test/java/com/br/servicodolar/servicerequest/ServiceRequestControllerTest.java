package com.br.servicodolar.servicerequest;

import com.br.servicodolar.servicerequest.controller.ServiceRequestController;
import com.br.servicodolar.servicerequest.domain.entity.Order;
import com.br.servicodolar.servicerequest.domain.entity.StatusOrder;
import com.br.servicodolar.servicerequest.usecase.InsertServiceRequest;
import com.br.servicodolar.servicerequest.usecase.model.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;


@WebMvcTest(ServiceRequestController.class)
public class ServiceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private InsertServiceRequest serviceRequest;

    @Test
    void SuccessRequestForPost() throws Exception {

        OrderDTO orderDTO = new OrderDTO(1l,
                2l,
                3l,
                StatusOrder.ABERTO,
                LocalDate.now(),
                LocalTime.of(8, 0),
                LocalDate.now(),
                LocalTime.of(9, 0));

        Mockito.when(serviceRequest.execute(Mockito.any())).thenReturn(new Order());

        mockMvc.perform(MockMvcRequestBuilders.post("/service-request")
                        .content(objectMapper.writeValueAsString(orderDTO)).contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
