package com.howtodoinjava.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.howtodoinjava.rest.entity.AlbumEntity;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

}
