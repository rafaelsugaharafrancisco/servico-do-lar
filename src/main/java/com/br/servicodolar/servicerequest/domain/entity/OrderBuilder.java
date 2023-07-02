package com.br.servicodolar.servicerequest.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrderBuilder {
    private Long costumerId;
    private Long serviceProviderId;
    private Long serviceId;
    private StatusOrder statusOrder;
    private Integer year;
    private LocalDate openingDate;
    private Double totalServiceCost;
    private Schedule schedule;
    private LocalDateTime updatedDateTime;

    public OrderBuilder setCostumerId(Long costumerId) {
        this.costumerId = costumerId;
        return this;
    }

    public OrderBuilder setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
        return this;
    }

    public OrderBuilder setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public OrderBuilder setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
        return this;
    }

    public OrderBuilder setYear(Integer year) {
        this.year = year;
        return this;
    }

    public OrderBuilder setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
        return this;
    }

    public OrderBuilder setTotalServiceCost(Double totalServiceCost) {
        this.totalServiceCost = totalServiceCost;
        return this;
    }

    public OrderBuilder setSchedule(LocalDate serviceStartDate, LocalTime serviceStartTime, LocalDate serviceFinishDate, LocalTime serviceFinishTime) {
        this.schedule = new Schedule(serviceStartDate, serviceStartTime, serviceFinishDate, serviceFinishTime);
        return this;
    }

    public OrderBuilder setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
        return this;
    }

    public Order createOrder() {
        return new Order(costumerId, serviceProviderId, serviceId, statusOrder, year, openingDate, totalServiceCost, schedule, updatedDateTime);
    }

}