package com.project.qeats.exchanges;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRestaurantsRequest {
	@NotNull
	@Min(value = 8)
	@Max(value = 36)
	private double latitude;
	  
	@NotNull
	@Min(value = 70)
	@Max(value = 96)
	private double longitude;
	
	private String searchFor;
	
	public GetRestaurantsRequest() {}

}
