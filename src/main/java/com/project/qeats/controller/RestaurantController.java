package com.project.qeats.controller;

import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.qeats.exchanges.GetRestaurantsRequest;
import com.project.qeats.exchanges.GetRestaurantsResponse;
import com.project.qeats.services.RestaurantService;

import lombok.extern.log4j.Log4j2;


@RestController
@Log4j2
public class RestaurantController {
	public static final String RESTAURANT_API_ENDPOINT = "/qeats/v1";
	public static final String WELCOME = "/welcome";
	public static final String RESTAURANTS_API = "/restaurants";
	public static final String RESTAURANTS_AVAILABLE = "/available";

	
	@Autowired
	private RestaurantService restaurantService; 
	
	
	// Welcome Method
	@GetMapping(RESTAURANT_API_ENDPOINT + WELCOME)
	public String WelcomeTOQEats() {
		return "Welcome to QEats!!";
	}
	
	//Get All Restaurants in Database
	@GetMapping(RESTAURANT_API_ENDPOINT + RESTAURANTS_API)
	public ResponseEntity<GetRestaurantsResponse> getAllRestaurants() {
	
		log.info("getAllRestaurants called");
	    GetRestaurantsResponse getRestaurantsResponse;
	
	    //CHECKSTYLE:ON
	    getRestaurantsResponse = restaurantService.findRestaurants();
	    log.info("Restaurants returned {}", getRestaurantsResponse);
	
	    return ResponseEntity.ok().body(getRestaurantsResponse);
	}
	
	// Get Available Restaurants Near Your Locality
	@GetMapping(RESTAURANT_API_ENDPOINT + RESTAURANTS_API + RESTAURANTS_AVAILABLE)
	public ResponseEntity<GetRestaurantsResponse> getAvailableRestaurants(
			@Valid @RequestBody GetRestaurantsRequest getRestaurantsRequest) {
	
		log.info("getRestaurants called with {}", getRestaurantsRequest);
	    GetRestaurantsResponse getRestaurantsResponse;
	
	    //CHECKSTYLE:ON
	    getRestaurantsResponse = restaurantService
	      .findAllRestaurantsCloseBy(getRestaurantsRequest, LocalTime.now());
	    log.info("getRestaurants returned {}", getRestaurantsResponse);
	
	    return ResponseEntity.ok().body(getRestaurantsResponse);
	  }
}
