package com.br.servicodolar.servicerequest.usecase.model;

import com.br.servicodolar.servicerequest.domain.entity.StatusOrder;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderDTO(

        @Positive(message = "Não pode ser negativo.")
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
