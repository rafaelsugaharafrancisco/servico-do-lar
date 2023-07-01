package com.br.servicodolar.orderofservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Embeddable
public class Schedule {

    @Column(name = "start_date", nullable = false)
    private final LocalDate serviceStartDate;
    @Column(name = "start_time", nullable = false)
    private final LocalTime serviceStartTime;
    @Column(name = "finish_date", nullable = false)
    private final LocalDate serviceFinishDate;
    @Column(name = "finish_time", nullable = false)
    private final LocalTime serviceFinishTime;

    public Schedule(LocalDate serviceStartDate, LocalTime serviceStartTime, LocalDate serviceFinishDate ,LocalTime serviceFinishTime) {
        this.notAllowSchedulingWhenStartDateGreaterFinishDate(serviceStartDate, serviceFinishDate);
        this.notAllowSchedulingWhenStartTimeGreaterFinishTime(serviceStartTime, serviceFinishTime);
        this.notAllowSchedulingAtSunday(serviceStartDate);
        this.notAllowSchedulingBeforeEightAmOrAfterSixPm(serviceStartTime);
        this.serviceStartDate = serviceStartDate;
        this.serviceStartTime = serviceStartTime;
        this.serviceFinishDate = serviceFinishDate;
        this.serviceFinishTime = serviceFinishTime;
    }

    private void notAllowSchedulingWhenStartDateGreaterFinishDate(LocalDate serviceStartDate, LocalDate serviceFinishDate) {

        if (serviceStartDate.isAfter(serviceFinishDate))
            throw new RuntimeException("Não é permitido agendamento com data de inicio maior que a data de termino.");
    }

    private void notAllowSchedulingWhenStartTimeGreaterFinishTime(LocalTime serviceStartTime, LocalTime serviceFinishTime) {

        if (serviceStartTime.isAfter(serviceFinishTime))
            throw new RuntimeException("Não é permitido agendamento com data de termino menor que a data de inicio.");
    }

    private void notAllowSchedulingAtSunday(LocalDate serviceStartDate) {

        if (DayOfWeek.SUNDAY.equals(serviceStartDate.getDayOfWeek()))
            throw new RuntimeException("Os dias de atendimento é de segunda à sabado.");
    }

    private void notAllowSchedulingBeforeEightAmOrAfterSixPm(LocalTime serviceStartTime) {

        final LocalTime EIGHT_AM = LocalTime.of(8, 0);
        final LocalTime SIX_PM = LocalTime.of(18, 0);

        if (serviceStartTime.isBefore(EIGHT_AM) || serviceStartTime.isAfter(SIX_PM))
            throw new RuntimeException("O horário de funcionamento é das 08:00 hs às 18:00 hs.");
    }
}
