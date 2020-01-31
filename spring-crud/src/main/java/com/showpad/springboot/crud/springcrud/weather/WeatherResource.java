package com.showpad.springboot.crud.springcrud.weather;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class WeatherResource {
    @Autowired
    private WeatherService weatherManagementService;

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
  
    // @PostMapping("/{location}")
    // public Weather searchWeather(@RequestBody String location) {
  
    //     Weather searchWeather = weatherManagementService.getWeatherInfo(location);
  
    //   // Location
    //   // Get current resource url
    //   /// {id}
    //   URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
    //       .toUri();
  
    //   return ResponseEntity.created(uri).build();
    // }
    
}
/*abstract@RestController : Combination of @Controller and @ResponseBody - Beans returned are converted to/from JSON/XML.
@Autowired private CoursesHardcodedService courseManagementService - Autowire the CoursesHardcodedService so that we can retrieve details from business service.
*/
/*abstract
@CrossOrigin(origins = { “http://localhost:3000”, “http://localhost:4200”, “http://localhost:8081” }) - Allow requests from specific origins
We will use 3000 to run React, and 8081 to run Vue JS apps, and we use 4200 to run Angular apps. Hence we are allowing requests from all ports.

*/