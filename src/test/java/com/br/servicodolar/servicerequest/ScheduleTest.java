package com.br.servicodolar.servicerequest;


import com.br.servicodolar.servicerequest.domain.entity.Schedule;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequestStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleTest {

    @Test
    void shouldThrowExceptionWhenDayOfMonthIsSunday() {

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 25));
        schedule.setServiceStartTime(LocalTime.of(9, 0));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 25));
        schedule.setServiceFinishTime(LocalTime.of(10,0));

        String message = Assertions.assertThrows(RuntimeException.class, () ->
                schedule.validateDateAndTime()).getMessage();

        Assertions.assertEquals("Os dias de atendimento é de segunda à sabado.", message);
    }

    @Test
    void shouldThrowExceptionWhenTimeIsBeforeEightAm() {

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceStartTime(LocalTime.of(7, 59));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceFinishTime(LocalTime.of(9, 0));

        String message = Assertions.assertThrows(RuntimeException.class, () ->
                schedule.validateDateAndTime()).getMessage();

        Assertions.assertEquals("O horário de funcionamento é das 08:00 hs às 18:00 hs.", message);


    }

    @Test
     void shouldThrowExceptionWhenTimeIsAfterSixPm() {

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceStartTime(LocalTime.of(18, 1));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceFinishTime(LocalTime.of(19, 0));

        String message = Assertions.assertThrows(RuntimeException.class,
                () -> schedule.validateDateAndTime()).getMessage();

        Assertions.assertEquals("O horário de funcionamento é das 08:00 hs às 18:00 hs.", message);
    }

    @Test
    void shouldThrowExceptionWhenStartDateGreaterFinishDate() {

        var schedule = new Schedule();
        schedule.setServiceStartDate(LocalDate.of(2023, 6, 27));
        schedule.setServiceStartTime(LocalTime.of(9, 1));
        schedule.setServiceFinishDate(LocalDate.of(2023, 6, 26));
        schedule.setServiceFinishTime(LocalTime.of(10, 0));

        String message = Assertions.assertThrows(RuntimeException.class,
                () -> schedule.validateDateAndTime()).getMessage();

        Assertions.assertEquals("Não é permitido agendamento com data de termino menor que a data de inicio.", message);
    }

//    @Test
//    void shouldThrowExceptionWhenStartTimeGreaterFinishTime() {
//
//        Assertions.assertThrows(RuntimeException.class,
//                () -> new Schedule(LocalDate.of(2023, 6, 26),
//                        LocalTime.of(9, 0),
//                        LocalDate.of(2023, 6, 26),
//                        LocalTime.of(8, 0)));
//    }
}
