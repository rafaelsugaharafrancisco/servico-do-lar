package com.br.servicodolar.servicerequest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Embeddable
@NoArgsConstructor
public class Schedule {

    @Column(name = "start_date", nullable = false)
    private LocalDate serviceStartDate;
    @Column(name = "start_time", nullable = false)
    private LocalTime serviceStartTime;
    @Column(name = "finish_date", nullable = false)
    private LocalDate serviceFinishDate;
    @Column(name = "finish_time", nullable = false)
    private LocalTime serviceFinishTime;

    public Schedule(LocalDate serviceStartDate, LocalTime serviceStartTime, LocalDate serviceFinishDate ,LocalTime serviceFinishTime) {
        this.notAllowDateAndTimeNull(serviceStartDate, serviceStartTime, serviceFinishDate ,serviceFinishTime);
        this.notAllowSchedulingWhenStartDateGreaterFinishDate(serviceStartDate, serviceFinishDate);
        this.notAllowSchedulingWhenStartTimeGreaterFinishTime(serviceStartTime, serviceFinishTime);
        this.notAllowSchedulingAtSunday(serviceStartDate);
        this.notAllowSchedulingBeforeEightAmOrAfterSixPm(serviceStartTime);
        this.serviceStartDate = serviceStartDate;
        this.serviceStartTime = serviceStartTime;
        this.serviceFinishDate = serviceFinishDate;
        this.serviceFinishTime = serviceFinishTime;
    }

    private void notAllowDateAndTimeNull(LocalDate serviceStartDate, LocalTime serviceStartTime, LocalDate serviceFinishDate, LocalTime serviceFinishTime) {
        if (serviceStartDate == null || serviceStartTime == null || serviceFinishDate == null || serviceFinishTime == null)
            throw new RuntimeException("Data e hora invalida.");
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
