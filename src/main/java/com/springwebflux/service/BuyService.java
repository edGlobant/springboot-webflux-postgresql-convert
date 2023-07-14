package com.springwebflux.service;

import com.springwebflux.model.request.BuyRequest;
import com.springwebflux.model.response.BuyResponse;
import reactor.core.publisher.Mono;

public interface BuyService {
    Mono<BuyResponse> buy(BuyRequest buyRequest);
}
