package com.br.servicodolar.orderofservice;

import com.br.servicodolar.orderofservice.client.ServiceAPI;
import com.br.servicodolar.orderofservice.client.model.ServiceDTO;
import com.br.servicodolar.orderofservice.domain.CostCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class CostCalculatorTest {

    @MockBean
    private ServiceAPI serviceAPI;

    @Test
    void shouldReturnValue300() {

        Mockito.when(serviceAPI.getOneService(1l)).thenReturn(
                Optional.of(new ServiceDTO(1l, "Trocar torneira", 1.5)));

        Assertions.assertEquals(300.0, new CostCalculator().execute(serviceAPI, 1l));

        Mockito.verify(serviceAPI).getOneService(1l);
    }

    @Test
    void shouldThrowExceptionWhenServiceNotFound() {

        Mockito.when(serviceAPI.getOneService(1l)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> new CostCalculator().execute(serviceAPI, 1l));

        Mockito.verify(serviceAPI).getOneService(1l);
    }
}
