package com.project.qeats.exchanges;

import java.util.List;

import com.project.qeats.dto.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRestaurantsResponse {
	List<Restaurant> restaurants;
}
