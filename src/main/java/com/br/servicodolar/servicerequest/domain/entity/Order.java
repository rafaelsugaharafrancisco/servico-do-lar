package com.br.servicodolar.servicerequest.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "costumer_id", nullable = false)
    private Long costumerId;

    @Column(name = "service_provider_id", nullable = false)
    private Long serviceProviderId;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "status_order", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StatusOrder statusOrder;

    @Column(name = "year", nullable = false, length = 4)
    private Integer year;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @Column(name = "total_cost", nullable = false)
    private Double totalServiceCost;

    @Embedded
    private Schedule schedule;
    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime;

    public void setCostumerId(Long costumerId) {

        if (costumerId <= 0)
            throw new RuntimeException("Código do cliente inválido.");

        this.costumerId = costumerId;
    }

    public void setServiceProviderId(Long serviceProviderId) {

        if (serviceProviderId <= 0)
            throw new RuntimeException("Códgido do prestador inválido.");

        this.serviceProviderId = serviceProviderId;
    }

    public void setServiceId(Long serviceId) {

        if (serviceId <= 0)
            throw new RuntimeException("Código do serviço inválido.");

        this.serviceId = serviceId;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        if (statusOrder == null)
            throw new RuntimeException("Status não pode ser nulo.");

        this.statusOrder = statusOrder;
    }

    public void setYear(Integer year) {
        if (year <= 0)
            throw new RuntimeException("O valor de ano tem que ser maior igual a zero.");

        this.year = year;
    }

    public void setOpeningDate(LocalDate openingDate) {
        if (openingDate == null)
            throw new RuntimeException("A data de abertura não pode ser nulo.");

        this.openingDate = openingDate;
    }

    public void setTotalServiceCost(Double totalServiceCost) {
        if (totalServiceCost <= 0)
            throw new RuntimeException("O valor total tem que ser maior igual a zero.");

        this.totalServiceCost = totalServiceCost;
    }

    public void setSchedule(Schedule schedule) {
        if (schedule == null)
            throw new RuntimeException("A agenda não pode ser nulo.");

        this.schedule = schedule;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        if (updatedDateTime == null)
            throw new RuntimeException("A data de atualização não pode ser nulo.");

        this.updatedDateTime = updatedDateTime;
    }
}

