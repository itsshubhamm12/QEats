package com.project.qeats.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.qeats.models.RestaurantEntity;

public interface RestaurantRepository extends MongoRepository<RestaurantEntity, String> {

	List<RestaurantEntity> findAll();
}
