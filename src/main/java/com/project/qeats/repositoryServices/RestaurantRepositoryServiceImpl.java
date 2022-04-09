package com.project.qeats.repositoryServices;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.qeats.dto.Restaurant;
import com.project.qeats.models.RestaurantEntity;
import com.project.qeats.repositories.RestaurantRepository;
import com.project.qeats.utils.GeoUtils;

@Repository
public class RestaurantRepositoryServiceImpl implements RestaurantRepositoryService{

	@Autowired //(required = true)
	private ModelMapper modelMapper;

	@Autowired
	private RestaurantRepository restaurantRepository;

	private boolean isOpenNow(LocalTime time, RestaurantEntity res) {
		LocalTime openingTime = LocalTime.parse(res.getOpensAt());
	  	LocalTime closingTime = LocalTime.parse(res.getClosesAt());

	    return time.isAfter(openingTime) && time.isBefore(closingTime);
	}

	public List<Restaurant> findAllRestaurantsCloseBy(Double latitude,
		Double longitude, LocalTime currentTime, Double servingRadiusInKms) {

	    List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();

	    List<Restaurant> restaurants = new ArrayList<>();

	    for (RestaurantEntity restaurantEntity : restaurantEntities) {
	      if (isRestaurantCloseByAndOpen(restaurantEntity, currentTime, latitude, longitude,
	          servingRadiusInKms)) {
	        restaurants.add(modelMapper.map(restaurantEntity, Restaurant.class));
	      }
	    }

	    return restaurants;
	}

	private boolean isRestaurantCloseByAndOpen(RestaurantEntity restaurantEntity,
	      LocalTime currentTime, Double latitude, Double longitude, Double servingRadiusInKms) {
		
	    if (isOpenNow(currentTime, restaurantEntity)) {
	      return GeoUtils.findDistanceInKm(latitude, longitude,
	          restaurantEntity.getLatitude(), restaurantEntity.getLongitude())
	          < servingRadiusInKms;
	    }

	    return false;
	  }

	public List<Restaurant> findAllRestaurants() {
		List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();

	    List<Restaurant> restaurants = new ArrayList<>();

	    for (RestaurantEntity restaurantEntity : restaurantEntities) {
	        restaurants.add(modelMapper.map(restaurantEntity, Restaurant.class));
	    }

	    return restaurants;
	}

}
