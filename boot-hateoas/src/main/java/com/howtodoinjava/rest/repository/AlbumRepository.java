package com.howtodoinjava.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howtodoinjava.rest.entity.AlbumEntity;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long>{

	/*public List<AlbumEntity> findByActors_Id(Long id); */

}
