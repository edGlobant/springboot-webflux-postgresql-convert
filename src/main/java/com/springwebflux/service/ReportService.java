package com.springwebflux.service;

import com.springwebflux.model.request.ReportRequest;
import com.springwebflux.model.response.ReportResponse;
import reactor.core.publisher.Mono;

public interface ReportService {
    Mono<ReportResponse> report(ReportRequest reportRequest);
}
