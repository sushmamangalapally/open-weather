package com.showpad.springboot.crud.springcrud.weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
// allowing requests from all ports.
@RestController
// @RestController: Combination of @Controller and @ResponseBody - Beans returned are converted to/from JSON/XML.
public class WeatherResource {
    @Autowired
    private WeatherService weatherManagementService;
    // WeatherService - Autowire the WeatherService so that we can retrieve details from business service.

    @GetMapping("/")
    public String getAllWeather() {
        if (weatherManagementService.getWeatherCitiesInfo() == null) {
            return "";
        }
        return weatherManagementService.getWeatherCitiesInfo().toString();
    }

    @GetMapping("/{location}")
    public Weather getWeather(@PathVariable String location) {
        return weatherManagementService.getWeatherInfo(location);
    }
}