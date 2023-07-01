package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.domain.ValidationScheduleInDB;
import com.br.servicodolar.servicerequest.domain.entity.Schedule;
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
        this.newSchedule = new Schedule(LocalDate.of(2023, 6, 26),
                LocalTime.of(10, 0),
                LocalDate.of(2023, 6, 26),
                LocalTime.of(11, 30));

        this.scheduleList = List.of(this.newSchedule);
    }

    @Test
    void shouldThrowExceptionWhenDateAndTimeServiceAlreadyExists() {

        var newSchedule = new Schedule(LocalDate.of(2023, 6, 26),
                LocalTime.of(10, 0),
                LocalDate.of(2023, 6, 26),
                LocalTime.of(10, 30));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationScheduleInDB().validateIfDateAndTimeExist(this.scheduleList, newSchedule));

        var newSchedule1 = new Schedule(LocalDate.of(2023, 6, 26),
                LocalTime.of(9, 0),
                LocalDate.of(2023, 6, 26),
                LocalTime.of(10, 1));

        Assertions.assertThrows(RuntimeException.class,
                () -> new ValidationScheduleInDB().validateIfDateAndTimeExist(this.scheduleList, newSchedule1));
    }
}
