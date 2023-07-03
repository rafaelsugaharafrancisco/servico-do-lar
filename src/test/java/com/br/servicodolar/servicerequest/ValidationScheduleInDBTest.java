package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.domain.ValidationScheduleInDB;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequestStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ValidationScheduleInDBTest {

    private List<Schedule> scheduleList;

    private Schedule newSchedule;

    @BeforeEach
    void createScheduleList() {

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceStartTime(LocalTime.of(10, 0));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceFinishTime(LocalTime.of(11,30));

        this.newSchedule = schedule;

        this.scheduleList = List.of(this.newSchedule);
    }

    @Test
    void shouldThrowExceptionWhenDateAndTimeServiceAlreadyExists() {

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationScheduleInDB().validateIfDateAndTimeExist(this.scheduleList, newSchedule));

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceStartTime(LocalTime.of(9, 0));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceFinishTime(LocalTime.of(10,30));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationScheduleInDB().validateIfDateAndTimeExist(this.scheduleList, schedule));
    }
}
