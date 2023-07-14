package com.springwebflux.service;

import com.springwebflux.model.request.ConvertRequest;
import com.springwebflux.model.response.ConvertResponse;
import reactor.core.publisher.Mono;

public interface ConvertService {
    Mono<ConvertResponse> convert(ConvertRequest convertRequest);
}
