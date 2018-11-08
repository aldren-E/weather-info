package com.weatherinfo.app.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.weatherinfo.app.config.WeatherAppProperties;
import com.weatherinfo.app.entity.WeatherInfo;
import com.weatherinfo.app.repository.WeatherInfoRepository;

@Service
public class WeatherService {
	
	private static final String WEATHER_URL =
			"http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	private final RestTemplate restTemplate;

	private final String apiKey;
	
	private final WeatherInfoRepository weatherInfoRepository;
	
	public WeatherService(RestTemplateBuilder restTemplateBuilder,
			WeatherAppProperties properties,
			WeatherInfoRepository weatherInfoRepository) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = properties.getApi().getKey();
		this.weatherInfoRepository = weatherInfoRepository;
	}

	public WeatherInfo getWeatherByCountryAndCity(String country, String city) {
		logger.info("Requesting current weather for {}/{}", country, city);
		URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
		WeatherInfo weatherInfo = restTemplate.getForObject(url, WeatherInfo.class);
		weatherInfo.setLocation(country+", "+city);
		return weatherInfo;
	}
	
	public List<WeatherInfo> getWeather() {
		List<WeatherInfo> list = new ArrayList<>();
		String[] countries = {"UK","Czech Republic","USA"};
		String[] cities = {"London","Prague","San Francisco"};
		for(int i=0; i<countries.length; i++) {
			String country = countries[i];
			String city = cities[i];
			WeatherInfo info = getWeatherByCountryAndCity(country, city);
			list.add(info);
		}
		
		save(list);
		
		return list;
	}
	
	public void save(List<WeatherInfo> list) {
		for(WeatherInfo info : list) {
			String responseId = UUID.randomUUID().toString();
			info.setId(null);
			info.setResponseId(responseId);
			info.setDtimeInserted(new Date());
		}
		weatherInfoRepository.saveAll(list);
	}
	
	public List<WeatherInfo> storeWeather() {
		int counter = 5;
		List<WeatherInfo> allList = new ArrayList<>();
		for(int i=0; i<counter; i++) {
			List<WeatherInfo> list = getWeather();
			allList.addAll(list);
		}
		return allList;
	}
	
}
