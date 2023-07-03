package com.br.servicodolar.servicerequest.repository;

import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findAllByYearAndCostumerId(Integer year, Long costumerId);

    List<ServiceRequest> findAllByYearAndServiceProviderId(Integer year, Long serviceProviderId);

    ServiceRequest save(ServiceRequest serviceRequest);
}
