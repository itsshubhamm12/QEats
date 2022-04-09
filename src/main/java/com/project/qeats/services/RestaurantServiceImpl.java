package com.project.qeats.services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.qeats.dto.Restaurant;
import com.project.qeats.exchanges.GetRestaurantsRequest;
import com.project.qeats.exchanges.GetRestaurantsResponse;
import com.project.qeats.repositoryServices.RestaurantRepositoryService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RestaurantServiceImpl implements RestaurantService{

	  private final Double peakHoursServingRadiusInKms = 3.0;
	  private final Double normalHoursServingRadiusInKms = 5.0;
	  
	  @Autowired
	  private RestaurantRepositoryService restaurantRepositoryService;

	  @Override
	  public GetRestaurantsResponse findAllRestaurantsCloseBy(
	      GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
	    
	    double latitude = getRestaurantsRequest.getLatitude();
	    double longitude = getRestaurantsRequest.getLongitude();
	    double servingRadiusInKms;

	    if (isPeakTime(currentTime)) {
	      servingRadiusInKms = peakHoursServingRadiusInKms;
	    } else {
	      servingRadiusInKms = normalHoursServingRadiusInKms;
	    }

	    List<Restaurant> restaurants = restaurantRepositoryService
	        .findAllRestaurantsCloseBy(latitude, longitude, currentTime, servingRadiusInKms);

	    log.info("Total fetched restaurants are: " + restaurants.size());
	    
	    return new GetRestaurantsResponse(restaurants); 
	  }

	  public Boolean isPeakTime(LocalTime currentTime) {
	    LocalTime temp = LocalTime.of(8, 1);

	    if (currentTime.isAfter(temp.minusMinutes(2)) && currentTime.isBefore(temp.plusHours(2))) {
	      return true;
	    }

	    temp = LocalTime.of(13, 1);

	    if (currentTime.isAfter(temp.minusMinutes(2)) && currentTime.isBefore(temp.plusHours(1))) {
	      return true;
	    }

	    temp = LocalTime.of(19, 1);

	    if (currentTime.isAfter(temp.minusMinutes(2)) && currentTime.isBefore(temp.plusHours(2))) {
	      return true;
	    }
	    
	    return false;
	  }

	@Override
	public GetRestaurantsResponse getAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepositoryService.getAllRestaurants();
		return new GetRestaurantsResponse(restaurants);
	}
	

}
