package com.br.servicodolar.servicerequest.usecase.model;

import com.br.servicodolar.servicerequest.domain.entity.StatusOrder;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderDTO(
        long costumerId,
        long serviceProviderId,
        long serviceId,
        StatusOrder statusOrder,
        LocalDate serviceStarDate,
        LocalTime serviceStartTime,
        LocalDate serviceFinishDate,
        LocalTime serviceFinishTime
) {
}
