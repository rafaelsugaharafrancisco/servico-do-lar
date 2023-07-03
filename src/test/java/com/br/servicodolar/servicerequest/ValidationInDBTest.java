package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.domain.ValidationInDBForCostumer;
import com.br.servicodolar.servicerequest.domain.ValidationInDBForServiceProvider;
import com.br.servicodolar.servicerequest.domain.ValidationScheduleInDB;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequestStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class ValidationInDBTest {

    private List<ServiceRequest> serviceRequestList;

    @BeforeEach
    void createScheduleList() {

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

        this.serviceRequestList = List.of(serviceRequest);
    }

    @Test
    void shouldThrowExceptionWhenSameServiceAlreadyExists() {

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

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForCostumer(this.serviceRequestList).validateIfServiceExistInDataBase(serviceRequest));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForCostumer(this.serviceRequestList).validateIfDateTimeOfServiceExistInDB(serviceRequest, new ValidationScheduleInDB()));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForServiceProvider(this.serviceRequestList).validateIfServiceExistInDataBase(serviceRequest));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationInDBForServiceProvider(this.serviceRequestList).validateIfDateTimeOfServiceExistInDB(serviceRequest, new ValidationScheduleInDB()));



    }

}
