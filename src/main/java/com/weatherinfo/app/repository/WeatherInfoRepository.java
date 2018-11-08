package com.weatherinfo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherinfo.app.entity.WeatherInfo;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {

}
