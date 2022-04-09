# QEats
A mock food ordering web application

# Overview:
QEats is a mock food ordering app that allows users to browse and order from nearby restaurants. This backend project uses Spring Boot application. 
Several REST API endpoints are implemented to query restaurant information and get nearby available restaurants
whereas Jackson is used for serialization/de-serialization of DTOs. MongoDB is used for database using Spring Data.
Unit testing is also integrated in the project with JUnit 5 and Mockito to make it robust and error free.

# Entry Point:
** QEatsApplication.java: Spring Boot Apllication class

# Controller:
** RestaurantController.java: It has 3 GET methods which are used for querying the restaurants.

# Service:
** RestaurantService.java: It has 2 methods to get restaurants close by and get all restaurants.

# Repository:
** RestaurantRepositoryService.java: It has 2 methods which fetch data from database as requested by service layer.

# Model:
** RestaurantEntity.java: This class is used for mapping documents from database.

# DTOs:
** Restaurant.java: This object is used data exchanges when queries about restaurants.
** GetRestaurantRequest.java: The exchange object used for querying nearby restaurants.
** GetRestaurantResponse.java: The exchange object used as response data for query of nearby restaurants.

# Tests:
** RestaurantControllerTest.java: All the negative and edge case test scenarios of RestaurantController class.

