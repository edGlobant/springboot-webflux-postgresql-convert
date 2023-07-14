package com.springwebflux.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.springwebflux.entity.ConvertTable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ConvertTableRepository extends ReactiveCrudRepository<ConvertTable, Long> {

	Flux<ConvertTable> findByConvertionId(String convertionId);

//	@Cacheable("convertTableCache")
//	public default Mono<ConvertTable> getConvertTableCacheList(String convertionId, String version) {
//		return this.findByConvertionIdAndVersion(convertionId, version);
//	}
}
