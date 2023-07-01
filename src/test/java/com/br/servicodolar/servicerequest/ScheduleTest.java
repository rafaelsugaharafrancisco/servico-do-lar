package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleTest {

    @Test
    void shouldThrowExceptionWhenDayOfMonthIsSunday() {

        Assertions.assertThrows(RuntimeException.class,
                () ->   new Schedule(LocalDate.of(2023, 6, 25),
                        LocalTime.of(8, 0),
                        LocalDate.of(2023, 6, 26),
                        LocalTime.of(9, 0)));
    }

    @Test
    void shouldThrowExceptionWhenTimeIsNotAllowed() {

        Assertions.assertThrows(RuntimeException.class,
                () -> new Schedule(LocalDate.of(2023, 6, 26),
                        LocalTime.of(7, 59),
                        LocalDate.of(2023, 6, 26),
                        LocalTime.of(9, 0)));

        Assertions.assertThrows(RuntimeException.class,
                () -> new Schedule(LocalDate.of(2023, 6, 26),
                        LocalTime.of(18, 1),
                        LocalDate.of(2023, 6, 26),
                        LocalTime.of(19, 0)));
    }

    @Test
    void shouldThrowExceptionWhenStartDateGreaterFinishDate() {

        Assertions.assertThrows(RuntimeException.class,
                () -> new Schedule(LocalDate.of(2023, 6, 28),
                        LocalTime.of(8, 0),
                        LocalDate.of(2023, 6, 27),
                        LocalTime.of(9, 0)));
    }

    @Test
    void shouldThrowExceptionWhenStartTimeGreaterFinishTime() {

        Assertions.assertThrows(RuntimeException.class,
                () -> new Schedule(LocalDate.of(2023, 6, 26),
                        LocalTime.of(9, 0),
                        LocalDate.of(2023, 6, 26),
                        LocalTime.of(8, 0)));
    }
}
