package com.howtodoinjava.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.howtodoinjava.rest.entity.AlbumEntity;

public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Long>{

}
