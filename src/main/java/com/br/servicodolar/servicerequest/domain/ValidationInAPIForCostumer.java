package com.br.servicodolar.servicerequest.domain;

import com.br.servicodolar.servicerequest.client.CostumerAPI;

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
