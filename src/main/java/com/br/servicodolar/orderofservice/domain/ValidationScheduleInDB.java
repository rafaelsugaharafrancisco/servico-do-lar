package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.domain.entity.Schedule;

import java.util.List;

public class ValidationScheduleInDB implements ValidationSchedule {

    @Override
    public void validateIfDateAndTimeExist(List<Schedule> scheduleList, Schedule newSchedule) {
        scheduleList.stream().forEach(oldSchedule -> {
            if (oldSchedule.getServiceStartDate().isEqual(newSchedule.getServiceStartDate())) {
                if ((newSchedule.getServiceStartTime().toSecondOfDay() >= oldSchedule.getServiceStartTime().toSecondOfDay()
                        && newSchedule.getServiceStartTime().toSecondOfDay() <= oldSchedule.getServiceFinishTime().toSecondOfDay())
                        || (newSchedule.getServiceFinishTime().toSecondOfDay() > oldSchedule.getServiceStartTime().toSecondOfDay()
                        && newSchedule.getServiceFinishTime().toSecondOfDay() <= oldSchedule.getServiceFinishTime().toSecondOfDay())) {
                    throw new RuntimeException("Já existe um agendamento nesse horário para esse pedido.");
                }
            }
        });
    }

}
