package com.howtodoinjava.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.howtodoinjava.rest.entity.ActorEntity;

public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

}
