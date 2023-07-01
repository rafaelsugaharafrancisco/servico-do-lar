package com.br.servicodolar.servicerequest;

import com.br.servicodolar.servicerequest.client.CostumerAPI;
import com.br.servicodolar.servicerequest.client.ServiceAPI;
import com.br.servicodolar.servicerequest.client.ServiceProviderAPI;
import com.br.servicodolar.servicerequest.domain.ValidationInAPIForCostumer;
import com.br.servicodolar.servicerequest.domain.ValidationInAPIForService;
import com.br.servicodolar.servicerequest.domain.ValidationInAPIForServiceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ValidationInAPITest {

    @MockBean
    private CostumerAPI costumerAPI;

    @MockBean
    private ServiceProviderAPI serviceProviderAPI;

    @MockBean
    private ServiceAPI serviceAPI;

    @Test
    void shouldReturnFalseWhenNotFound() {
        Mockito.when(costumerAPI.activeCostumerExists(1L)).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> new ValidationInAPIForCostumer(costumerAPI).validateIfExistWithAPI(1l));
        Mockito.verify(costumerAPI).activeCostumerExists(1l);

        Mockito.when(serviceAPI.serviceExists(1L)).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> new ValidationInAPIForService(serviceAPI).validateIfExistWithAPI(1l));
        Mockito.verify(serviceAPI).serviceExists(1l);

        Mockito.when(serviceProviderAPI.activeServiceProviderExists(1L)).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> new ValidationInAPIForServiceProvider(serviceProviderAPI).validateIfExistWithAPI(1l));
        Mockito.verify(serviceProviderAPI).activeServiceProviderExists(1l);
    }
}
