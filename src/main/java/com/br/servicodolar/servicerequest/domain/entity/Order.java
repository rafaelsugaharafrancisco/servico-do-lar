package com.br.servicodolar.servicerequest.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Não pode ser negativo porra!")
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

    public Order(Long costumerId,
                 Long serviceProviderId,
                 Long serviceId,
                 StatusOrder statusOrder,
                 Integer year,
                 LocalDate openingDate,
                 Double totalServiceCost,
                 Schedule schedule,
                 LocalDateTime updatedDateTime) {
        this.validateCostumerId(costumerId);
        this.validateServiceProviderId(serviceProviderId);
        this.validateServiceId(serviceId);
        this.validateStatusOrder(statusOrder);
        this.validateYear(year);
        this.validateOpeningDate(openingDate);
        this.validateTotalServiceCost(totalServiceCost);
        this.validateUpdatedDateTime(updatedDateTime);
        this.costumerId = costumerId;
        this.serviceProviderId = serviceProviderId;
        this.serviceId = serviceId;
        this.statusOrder = statusOrder;
        this.year = year;
        this.openingDate = openingDate;
        this.totalServiceCost = totalServiceCost;
        this.schedule = schedule;
        this.updatedDateTime = updatedDateTime;
    }

    private void validateCostumerId(Long costumerId) {

        if (costumerId == null)
            throw new RuntimeException("Código do cliente não pode ser nulo.");

        if (costumerId <= 0)
            throw new RuntimeException("Código do cliente não pode ser menor igual a zero.");

        this.costumerId = costumerId;
    }

    private void validateServiceProviderId(Long serviceProviderId) {

        if (serviceProviderId == null)
            throw new RuntimeException("Código do prestador não pode ser nulo.");

        if (serviceProviderId <= 0)
            throw new RuntimeException("Código do prestador não pode ser menor ou igual a zero.");

        this.serviceProviderId = serviceProviderId;
    }

    private void validateServiceId(Long serviceId) {

        if (serviceId == null)
            throw new RuntimeException("Código do serviço não pode ser nulo.");

        if (serviceId <= 0)
            throw new RuntimeException("Código do serviço não pode ser menor ou igual a zero.");

        this.serviceId = serviceId;
    }

    private void validateStatusOrder(StatusOrder statusOrder) {
        if (statusOrder == null)
            throw new RuntimeException("Status não pode ser nulo.");

        this.statusOrder = statusOrder;
    }

    private void validateYear(Integer year) {

        if (year == null)
            throw new RuntimeException("O ano não pode ser nulo.");

        if (year <= 0)
            throw new RuntimeException("O valor de ano tem que ser maior igual a zero.");

        this.year = year;
    }

    private void validateOpeningDate(LocalDate openingDate) {
        if (openingDate == null)
            throw new RuntimeException("A data de abertura não pode ser nulo.");

        this.openingDate = openingDate;
    }

    private void validateTotalServiceCost(Double totalServiceCost) {

        if (totalServiceCost == null)
            throw new RuntimeException("O custo total do serviço não pode ser nulo");

        if (totalServiceCost <= 0)
            throw new RuntimeException("O valor total tem que ser maior igual a zero.");

        this.totalServiceCost = totalServiceCost;
    }

    private void validateUpdatedDateTime(LocalDateTime updatedDateTime) {
        if (updatedDateTime == null)
            throw new RuntimeException("A data de atualização não pode ser nulo.");

        this.updatedDateTime = updatedDateTime;
    }
}

