package com.br.servicodolar.servicerequest.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class ServiceRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Positive(message = "Código do cliente não pode ser menor ou igual a zero.")
    @NotNull(message = "Código do cliente não pode ser nulo.")
    @Column(name = "costumer_id", nullable = false)
    private Long costumerId;

    @Setter
    @Positive(message = "Código do prestador não pode ser menor ou igual a zero.")
    @NotNull(message = "Código do prestador não pode ser nulo.")
    @Column(name = "service_provider_id", nullable = false)
    private Long serviceProviderId;

    @Setter
    @Positive(message = "Código do serviço não pode ser menor ou igual a zero.")
    @NotNull(message = "Código do serviço não pode ser nulo.")
    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Setter
    @NotNull(message = "Código do serviço não pode ser nulo.")
    @Column(name = "status_order", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ServiceRequestStatus serviceRequestStatus;

    @Column(name = "year", nullable = false, length = 4)
    private Integer year = LocalDate.now().getYear();

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate = LocalDate.now();

    @Setter
    @Positive(message = "O custo do serviço não pode ser menor ou igual a zero.")
    @NotNull(message = "Custo do serviço não pode ser nulo.")
    @Column(name = "total_cost", nullable = false)
    private Double totalServiceCost;

    @Embedded
    @Valid
    @Setter
    private Schedule schedule;

    @Column(name = "updated_date_time")
    private LocalDateTime updatedDateTime = LocalDateTime.now();
}

