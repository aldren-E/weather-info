package com.weatherinfo.app.rest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.weatherinfo.app.entity.WeatherInfo;
import com.weatherinfo.app.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeaterInfoRestController {
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping(path = "/{country}/{city}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WeatherInfo getWeatherByCountryAndCity(@PathVariable String country,
			@PathVariable String city) {
		return weatherService.getWeatherByCountryAndCity(country, city);
	}
	
	@GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<WeatherInfo> getWeather() {
		return weatherService.getWeather();
	}
	
	@RequestMapping(path = "/store", method = RequestMethod.POST)
	public List<WeatherInfo> storeWeather() {
		return weatherService.storeWeather();
	}

}
