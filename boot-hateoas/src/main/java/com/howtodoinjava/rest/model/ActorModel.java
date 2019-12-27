package com.howtodoinjava.rest.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "actor")
@Relation(collectionRelation = "actors")
@JsonInclude(Include.NON_NULL)
public class ActorModel extends RepresentationModel<ActorModel> 
{
	private Long id;
	private String firstName;
	private String lastName;
	private String birthDate;
	
	private List<AlbumModel> albums;
}
