package com.br.servicodolar.orderofservice.client;

import org.springframework.web.bind.annotation.PathVariable;

public interface CostumerAPI {

    boolean activeCostumerExists(@PathVariable long id);
}
