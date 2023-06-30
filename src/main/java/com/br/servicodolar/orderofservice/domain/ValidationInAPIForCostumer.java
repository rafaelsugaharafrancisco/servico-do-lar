package com.br.servicodolar.orderofservice.domain;

import com.br.servicodolar.orderofservice.client.CostumerAPI;
import com.br.servicodolar.orderofservice.domain.entity.Order;
import com.br.servicodolar.orderofservice.domain.entity.Schedule;

import java.util.List;
import java.util.Optional;

public class ValidationInAPIForCostumer implements ValidationInAPI {

    private CostumerAPI costumerAPI;

    public ValidationInAPIForCostumer(CostumerAPI costumerAPI) {
        this.costumerAPI = costumerAPI;
    }

    @Override
    public void validateIfExistWithAPI(long id) {
        boolean exist = this.costumerAPI.activeCostumerExists(id);

        if (!exist)
            throw new RuntimeException("Cliente não existe");
    }
}
