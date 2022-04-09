package com.project.qeats.controller;

import static com.project.qeats.controller.RestaurantController.RESTAURANTS_API;
import static com.project.qeats.controller.RestaurantController.RESTAURANT_API_ENDPOINT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.qeats.QEatsApplication;
import com.project.qeats.services.RestaurantService;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(classes = {QEatsApplication.class})
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
@SuppressWarnings({"unused", "deprecation"})
public class RestaurantControllerTest {

  private static final String RESTAURANT_API_URI = RESTAURANT_API_ENDPOINT + RESTAURANTS_API;

  private ObjectMapper objectMapper;

  private MockMvc mvc;

  @MockBean
  private RestaurantService restaurantService;

  @InjectMocks
  private RestaurantController restaurantController;

@BeforeEach
  public void setup() {
    objectMapper = new ObjectMapper();

    MockitoAnnotations.initMocks(this);

    mvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
  }


  @Test
  public void invalidLatitudeResultsInBadHttpRequest() throws Exception {
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("latitude", "91")
        .queryParam("longitude", "20")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?latitude=91&longitude=20", uri.toString());

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("latitude", "-91")
        .queryParam("longitude", "20")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?latitude=-91&longitude=20", uri.toString());

    response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  //-90 TO 90 latitude
  @Test
  public void invalidLongitudeResultsInBadHttpRequest() throws Exception {
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("latitude", "10")
        .queryParam("longitude", "181")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?latitude=10&longitude=181", uri.toString());


    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("latitude", "10")
        .queryParam("longitude", "-181")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?latitude=10&longitude=-181", uri.toString());

    response = mvc.perform(
        get(uri.toString()).accept("application/json")
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void incorrectlySpelledLongitudeParamResultsInBadHttpRequest() throws Exception {
    // mocks not required, since validation will fail before that.
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("latitude", "10")
        .queryParam("longitue", "20")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?latitude=10&longitue=20", uri.toString());


    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void incorrectlySpelledLatitudeParamResultsInBadHttpRequest() throws Exception {
    // mocks not required, since validation will fail before that.
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("laitude", "10")
        .queryParam("longitude", "20")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?laitude=10&longitude=20", uri.toString());


    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void noRequestParamResultsInBadHttpReuest() throws Exception {
    // mocks not required, since validation will fail before that.
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .build().toUri();

    assertEquals(RESTAURANT_API_URI, uri.toString());

    // calling api without latitude and longitude
    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void missingLongitudeParamResultsInBadHttpRequest() throws Exception {
    // calling api without latitude
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("latitude", "20.21")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?latitude=20.21", uri.toString());

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void missingLatitudeParamResultsInBadHttpRequest() throws Exception {
    // calling api without longitude
    URI uri = UriComponentsBuilder
        .fromPath(RESTAURANT_API_URI)
        .queryParam("longitude", "30.31")
        .build().toUri();

    assertEquals(RESTAURANT_API_URI + "?longitude=30.31", uri.toString());

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_UTF8)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }
}

