package com.br.servicodolar.orderofservice.usecase.model;

import com.br.servicodolar.orderofservice.domain.entity.StatusOrder;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderOfServiceDTO(
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
