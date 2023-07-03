package com.br.servicodolar.servicerequest.usecase;

import com.br.servicodolar.servicerequest.client.CostumerAPI;
import com.br.servicodolar.servicerequest.client.ServiceAPI;
import com.br.servicodolar.servicerequest.client.ServiceProviderAPI;
import com.br.servicodolar.servicerequest.domain.*;
import com.br.servicodolar.servicerequest.domain.entity.ServiceRequest;
import com.br.servicodolar.servicerequest.repository.ServiceRequestRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InsertServiceRequest {

    private CostumerAPI costumerAPI;
    private ServiceRequestRepository serviceRequestRepository;
    private ServiceAPI serviceAPI;
    private ServiceProviderAPI serviceProviderAPI;


    @Transactional
    public ServiceRequest execute(ServiceRequest newServiceRequest) {

        newServiceRequest.getSchedule().validateDateAndTime();

        validateInAPI(newServiceRequest);

        validateInDB(newServiceRequest);

        return this.serviceRequestRepository.save(newServiceRequest);

    }

    private void validateInDB(ServiceRequest serviceRequest) {
        ValidationSchedule validationSchedule = new ValidationScheduleInDB();

        List<ServiceRequest> serviceRequestList = this.serviceRequestRepository.findAllByYearAndCostumerId(serviceRequest.getYear(), serviceRequest.getCostumerId());
        ValidationInDB validationInDB = new ValidationInDBForCostumer(serviceRequestList);
        validationInDB.validateIfServiceExistInDataBase(serviceRequest);
        validationInDB.validateIfDateTimeOfServiceExistInDB(serviceRequest, validationSchedule);

        serviceRequestList = this.serviceRequestRepository.findAllByYearAndServiceProviderId(serviceRequest.getYear(), serviceRequest.getServiceProviderId());
        validationInDB = new ValidationInDBForServiceProvider(serviceRequestList);
        validationInDB.validateIfServiceExistInDataBase(serviceRequest);
        validationInDB.validateIfDateTimeOfServiceExistInDB(serviceRequest, validationSchedule);
    }

    private void validateInAPI(ServiceRequest serviceRequest) {
        ValidationInAPI validationInAPI  = new ValidationInAPIForCostumer(costumerAPI);
        validationInAPI.validateIfExistWithAPI(serviceRequest.getCostumerId());

        validationInAPI = new ValidationInAPIForService(serviceAPI);
        validationInAPI.validateIfExistWithAPI(serviceRequest.getServiceId());

        validationInAPI = new ValidationInAPIForServiceProvider(serviceProviderAPI);
        validationInAPI.validateIfExistWithAPI(serviceRequest.getServiceProviderId());
    }

//    private ServiceRequest getOrder(OrderDTO dto) {
//
//        double totalCost = 1000.00;//new CostCalculator().execute(this.serviceAPI, dto.serviceId());
//
//        var orderBuilder = new OrderBuilder();
//        orderBuilder.setCostumerId(dto.costumerId())
//                    .setServiceProviderId(dto.serviceProviderId())
//                    .setServiceId(dto.serviceId())
//                    .setServiceRequestStatus(ServiceRequestStatus.ABERTO)
//                    .setYear(LocalDate.now().getYear())
//                    .setOpeningDate(LocalDate.now())
//                    .setSchedule(dto.serviceStarDate(), dto.serviceStartTime(), dto.serviceFinishDate(), dto.serviceFinishTime())
//                    .setTotalServiceCost(totalCost)
//                    .setUpdatedDateTime(LocalDateTime.now());
//
//        return orderBuilder.createOrder();
//
//    }

}
