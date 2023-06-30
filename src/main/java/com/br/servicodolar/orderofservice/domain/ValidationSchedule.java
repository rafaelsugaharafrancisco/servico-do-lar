package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.domain.entity.Schedule;

import java.util.List;

public interface ValidationSchedule {

    void validateIfDateAndTimeExist(List<Schedule> scheduleList, Schedule newSchedule);
}
