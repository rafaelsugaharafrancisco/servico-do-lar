package com.br.servicodolar.servicerequest;

import com.br.servicodolar.servicerequest.controller.ServiceRequestController;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequestStatus;
import com.br.servicodolar.servicerequest.usecase.InsertServiceRequest;
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
    private InsertServiceRequest insertServiceRequest;

    @Test
    void SuccessRequestForPost() throws Exception {

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceStartTime(LocalTime.of(9, 0));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceFinishTime(LocalTime.of(10,0));

        var serviceRequest = new ServiceRequest();
        serviceRequest.setCostumerId(1l);
        serviceRequest.setServiceProviderId(2l);
        serviceRequest.setServiceId(3l);
        serviceRequest.setServiceRequestStatus(ServiceRequestStatus.ABERTO);
        serviceRequest.setTotalServiceCost(1000.20);
        serviceRequest.setSchedule(schedule);

        Mockito.when(insertServiceRequest.execute(Mockito.any())).thenReturn(serviceRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/service-request")
                        .content(objectMapper.writeValueAsString(serviceRequest)).contentType(MediaType.APPLICATION_JSON))
                            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
