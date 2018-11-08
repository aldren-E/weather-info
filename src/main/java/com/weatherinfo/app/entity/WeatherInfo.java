package com.weatherinfo.app.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "WeatherLog")
public class WeatherInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String responseId;
	private String location;
	private String actualWeather;
	private String temperature;
	private Date dtimeInserted;
	
	public WeatherInfo() {
	}

	

	
	public Long getId() {
		return Id;
	}




	public void setId(Long id) {
		Id = id;
	}




	public String getResponseId() {
		return responseId;
	}




	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getActualWeather() {
		return actualWeather;
	}




	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}




	public String getTemperature() {
		return temperature;
	}




	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}




	public Date getDtimeInserted() {
		return dtimeInserted;
	}




	public void setDtimeInserted(Date dtimeInserted) {
		this.dtimeInserted = dtimeInserted;
	}




	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setActualWeather(weather.get("main").toString());
	}
	
	
	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		setTemperature(main.get("temp").toString());
	}


}
