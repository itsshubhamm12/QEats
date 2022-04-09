package com.project.qeats.services;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.project.qeats.exchanges.GetRestaurantsRequest;
import com.project.qeats.exchanges.GetRestaurantsResponse;

@Service
public interface RestaurantService {
	  GetRestaurantsResponse findAllRestaurantsCloseBy(GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime);
	  GetRestaurantsResponse findRestaurants();
}
