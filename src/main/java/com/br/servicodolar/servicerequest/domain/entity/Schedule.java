package com.br.servicodolar.servicerequest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Embeddable
@NoArgsConstructor
public class Schedule {

    @Setter
    @NotNull(message = "Data de início não pode ser nulo.")
//    @FutureOrPresent(message = "Data de inicio não pode ser menor que a data atual.")
    @Column(name = "start_date", nullable = false)
    private LocalDate serviceStartDate;

    @Setter
    @NotNull(message = "Hora de início não pode ser nulo.")
//    @FutureOrPresent(message = "Hora de inicio não pode ser menor que a hora atual.")
    @Column(name = "start_time", nullable = false)
    private LocalTime serviceStartTime;

    @Setter
    @NotNull(message = "Data final não pode ser nulo.")
//    @FutureOrPresent(message = "Data final não pode ser menor que a data atual.")
    @Column(name = "finish_date", nullable = false)
    private LocalDate serviceFinishDate;

    @Setter
    @NotNull(message = "Hora final não pode ser nulo.")
//    @FutureOrPresent(message = "Hora final não pode ser menor que a hora atual.")
    @Column(name = "finish_time", nullable = false)
    private LocalTime serviceFinishTime;

    public void validateDateAndTime() {
        this.notAllowSchedulingBeforeEightAmOrAfterSixPm();
        this.notAllowSchedulingWhenStartDateGreaterFinishDate();
        this.notAllowSchedulingAtSunday();
    }

    private void notAllowSchedulingWhenStartDateGreaterFinishDate() {

        if (serviceStartDate.isAfter(serviceFinishDate))
            throw new RuntimeException("Não é permitido agendamento com data de termino menor que a data de inicio.");
    }

    private void notAllowSchedulingAtSunday() {

        if (DayOfWeek.SUNDAY.equals(serviceStartDate.getDayOfWeek()))
            throw new RuntimeException("Os dias de atendimento é de segunda à sabado.");
    }

    private void notAllowSchedulingBeforeEightAmOrAfterSixPm() {

        final LocalTime EIGHT_AM = LocalTime.of(8, 0);
        final LocalTime SIX_PM = LocalTime.of(18, 0);

        if (serviceStartTime.isBefore(EIGHT_AM) || serviceStartTime.isAfter(SIX_PM))
            throw new RuntimeException("O horário de funcionamento é das 08:00 hs às 18:00 hs.");
    }
}
