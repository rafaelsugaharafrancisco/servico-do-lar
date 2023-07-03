package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.client.CostumerAPI;
import com.br.servicodolar.servicerequest.client.ServiceAPI;
import com.br.servicodolar.servicerequest.client.ServiceProviderAPI;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequestStatus;
import com.br.servicodolar.servicerequest.repository.ServiceRequestRepository;
import com.br.servicodolar.servicerequest.usecase.InsertServiceRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@SpringBootTest
public class InsertServiceRequestTest {

    @MockBean
    private CostumerAPI costumerAPI;

    @MockBean
    private ServiceRequestRepository serviceRequestRepository;

    @MockBean
    private ServiceAPI serviceAPI;

    @MockBean
    private ServiceProviderAPI serviceProviderAPI;

    @Test
    void test() {

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


        Mockito.when(serviceRequestRepository.findAllByYearAndCostumerId(2023, 1l)).thenReturn(Collections.emptyList());
        Mockito.when(serviceRequestRepository.findAllByYearAndServiceProviderId(2023, 2l)).thenReturn(Collections.emptyList());
        Mockito.when(costumerAPI.activeCostumerExists(1l)).thenReturn(true);
        Mockito.when(serviceProviderAPI.activeServiceProviderExists(2l)).thenReturn(true);
        Mockito.when(serviceAPI.serviceExists(3l)).thenReturn(true);
        Mockito.when(serviceRequestRepository.save(Mockito.any(ServiceRequest.class))).thenReturn(serviceRequest);

        new InsertServiceRequest(costumerAPI, serviceRequestRepository, serviceAPI, serviceProviderAPI).execute(serviceRequest);
        Mockito.verify(serviceRequestRepository).findAllByYearAndCostumerId(Mockito.anyInt(), Mockito.anyLong());
        Mockito.verify(serviceRequestRepository).findAllByYearAndServiceProviderId(Mockito.anyInt(), Mockito.anyLong());
        Mockito.verify(costumerAPI).activeCostumerExists(Mockito.anyLong());
        Mockito.verify(serviceAPI).serviceExists(Mockito.anyLong());
        Mockito.verify(serviceProviderAPI).activeServiceProviderExists(Mockito.anyLong());
        Mockito.verify(serviceRequestRepository).save(Mockito.any(ServiceRequest.class));
    }

}
