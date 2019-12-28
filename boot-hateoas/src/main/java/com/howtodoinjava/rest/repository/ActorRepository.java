package com.howtodoinjava.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howtodoinjava.rest.entity.ActorEntity;

public interface ActorRepository extends PagingAndSortingRepository<ActorEntity, Long>{

}
