package com.springwebflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.springwebflux.entity.ConvertTable;

import reactor.core.publisher.Mono;

@Repository
public interface ConvertTableRepository extends ReactiveCrudRepository<ConvertTable, Long> {

	Mono<ConvertTable> findByConvertionIdAndVersion(String convertionId, String version);
}
