package com.howtodoinjava.rest.assembers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.howtodoinjava.rest.entity.ActorEntity;
import com.howtodoinjava.rest.entity.AlbumEntity;
import com.howtodoinjava.rest.model.ActorModel;
import com.howtodoinjava.rest.model.AlbumModel;
import com.howtodoinjava.rest.web.WebController;

@Component
public class ActorModelAssembler 
	extends RepresentationModelAssemblerSupport<ActorEntity, ActorModel> { 

	public ActorModelAssembler() {
		super(WebController.class, ActorModel.class);
	}

	@Override
	public ActorModel toModel(ActorEntity entity) 
	{
		ActorModel actorModel = instantiateModel(entity);
		
		actorModel.add(linkTo(
				methodOn(WebController.class)
				.getActorById(entity.getId()))
				.withSelfRel());
		
		actorModel.setId(entity.getId());
		actorModel.setFirstName(entity.getFirstName());
		actorModel.setLastName(entity.getLastName());
		actorModel.setBirthDate(entity.getBirthDate());
		actorModel.setAlbums(toAlbumModel(entity.getAlbums()));
		return actorModel;
	}
	
	@Override
	public CollectionModel<ActorModel> toCollectionModel(Iterable<? extends ActorEntity> entities) 
	{
		CollectionModel<ActorModel> actorModels = super.toCollectionModel(entities);
		
		actorModels.add(linkTo(methodOn(WebController.class).getAllActors()).withSelfRel());
		
		return actorModels;
	}

	private List<AlbumModel> toAlbumModel(List<AlbumEntity> albums) {
		if (albums.isEmpty())
			return Collections.emptyList();

		return albums.stream()
				.map(album -> AlbumModel.builder()
						.id(album.getId())
						.title(album.getTitle())
						.build()
						.add(linkTo(
								methodOn(WebController.class)
								.getAlbumById(album.getId()))
								.withSelfRel()))
				.collect(Collectors.toList());
	}
}
