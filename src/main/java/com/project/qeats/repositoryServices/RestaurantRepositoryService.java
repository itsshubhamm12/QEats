package com.project.qeats.repositoryServices;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.qeats.dto.Restaurant;

@Repository
public interface RestaurantRepositoryService {
	List<Restaurant> findAllRestaurantsCloseBy(Double latitude, Double longitude,
	      LocalTime currentTime, Double servingRadiusInKms);
	
	List<Restaurant> findAllRestaurants();
}
