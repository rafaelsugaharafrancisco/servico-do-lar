package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.domain.entity.Schedule;

import java.util.List;

public interface ValidationSchedule {

    void validateIfDateAndTimeExist(List<Schedule> scheduleList, Schedule newSchedule);
}
