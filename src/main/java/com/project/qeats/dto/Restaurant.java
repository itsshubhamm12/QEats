package com.project.qeats.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
public class Restaurant {

	private String restaurantId;
	private String name;
	private String city;
	private String imageUrl;
	private double latitude;
	private double longitude;
	private String opensAt;
	private String closesAt;
	@JsonProperty("attributes")
	private List<String> cuisines;

	public Restaurant(){}
}
