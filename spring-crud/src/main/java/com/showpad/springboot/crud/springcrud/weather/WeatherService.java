package com.showpad.springboot.crud.springcrud.weather;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.json.JSONArray;
import com.showpad.springboot.crud.springcrud.api.getRequests;

@Service
public class WeatherService {

    private static List<Weather> weatherList = new ArrayList<>();
    private static List<String> cityList = new ArrayList<>();

    static {
        if (WeatherService.weatherList.size() == 0) {
            String response = getRequests.getWeatherRequests("San Francisco");  
            System.out.println("static");
            String forecastResponse = getRequests.getForecastForWeather("San Francisco");      
            System.out.println(forecastResponse);
            
            if (response.length() != 0) {
                JSONObject jsonObject = new JSONObject(response);
                String fullInfoToString = jsonObject.toString();

                JSONObject main = (JSONObject) jsonObject.get("main");
                double temperature = (double) main.get("temp");
                JSONArray weather = (JSONArray) jsonObject.get("weather");
                JSONObject weatherObject = weather.getJSONObject(0);
                JSONObject jsonForecastObject = new JSONObject(forecastResponse);
                JSONArray forecastObjList = (JSONArray) jsonForecastObject.get("list");
                System.out.println(forecastObjList);
                String forecastList = forecastObjList.toString();


                String description = (String) weatherObject.get("description");
                String icon = (String) weatherObject.get("icon");
                Integer idCounter = (Integer) jsonObject.get("id");
                Weather sanFrancisco = new Weather(idCounter, "San Francisco", description, temperature, icon, forecastList, fullInfoToString);
                Boolean canAdd = true;
                for (Weather getWeather : weatherList) {
                    // String response = getRequests.getWeatherRequests(weather.getLocation());
                    // JSONObject cityObject = new JSONObject(response);
                    if (getWeather.getId() == idCounter) {
                        canAdd = false;
                    }
                }
                if (canAdd == true) {
                    System.out.println("special banana");
                    weatherList.add(sanFrancisco);
                    System.out.println("sanFrancisco");
                    System.out.println(sanFrancisco.getForecast());
                    System.out.println(weatherList);
                    cityList.add("san%20francisco");
                }
            }
        }
    }

    public List<Weather> findAll() {
        return weatherList;
    }

    public List<String> findAllCities() {
        return cityList;
    }

    public Weather findById(long id) {
        for (Weather weather : weatherList) {
            if (weather.getId() == id) {
                return weather;
            }
        }
        return null;
    }

    public JSONObject getWeatherCitiesInfo() {
        System.out.println("getWeatherCitiesInfo1");
        List<Integer> cityIdsList = new ArrayList<>();
        Integer count = 0;
        Weather firstWeather = null;
        for (Weather weather : weatherList) {
            count += 1;
            // if (count == 1) {
                firstWeather = weather;
            // }
            cityIdsList.add(weather.getId());
            System.out.println(weather.getId());
            // String response = getRequests.getWeatherRequests(weather.getLocation());
            // different kind of solutions
            // can store in object and update every time refresh nav happens or visit city again
        }

        String idsParam = "";
        for (int counter = 0; counter < cityIdsList.size(); counter++) { 		      
            idsParam += Integer.toString(cityIdsList.get(counter));
            if (counter != (cityIdsList.size()-1)) {
                idsParam += ",";
            }		
        }
        System.out.println("idsParam");
        System.out.println(idsParam);

        String response = getRequests.getAllWeatherRequests(idsParam);

        
        String weatherResponse = getRequests.getWeatherRequests(firstWeather.getLocation());  
        // don't have to use getAllWeatherRequests function
        // can use getId loop in cityIdsList
        // and reset everytime with setDescription, setTemperature, and setIcon
        System.out.println(firstWeather.getLocation());
        String forecastResponse = getRequests.getForecastForWeather(firstWeather.getLocation());      

        Weather cityForecast = getWeatherInfo(firstWeather.getLocation());
/*
        if (weatherResponse.length() != 0) {
            JSONObject jsonObject = new JSONObject(weatherResponse);

            JSONObject main = (JSONObject) jsonObject.get("main");
            Double temperature = (Double) main.get("temp");
            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = weather.getJSONObject(0);
            JSONObject jsonForecastObject = new JSONObject(forecastResponse);
            JSONArray forecastObjList = (JSONArray) jsonForecastObject.get("list");
            System.out.println(forecastObjList);
            String forecastList = forecastObjList.toString();


            String description = (String) weatherObject.get("description");
            String icon = (String) weatherObject.get("icon");
            Integer idCounter = (Integer) jsonObject.get("id");
            Weather sanFrancisco = new Weather(idCounter, "San Francisco", description, temperature, icon, forecastList);
            Boolean canAdd = true;
            for (Weather getWeather : weatherList) {
                // String response = getRequests.getWeatherRequests(weather.getLocation());
                // JSONObject cityObject = new JSONObject(response);
                if (getWeather.getId() == idCounter) {
                    canAdd = false;
                }
            }
            if (canAdd == true) {
                System.out.println("special banana");
                weatherList.add(sanFrancisco);
                System.out.println("sanFrancisco");
                System.out.println(sanFrancisco.getForecast());
                System.out.println(weatherList);
                cityList.add("san%20francisco");
            }
        }

*/



        if (response.length() != 0) {
            System.out.println("jsonObject");
            System.out.println(cityForecast.getForecast());
            JSONArray city = new JSONArray(cityForecast.getForecast());
            // response
            // return new JSONObject(cityForecast.getForecast());
            JSONObject jsonObject = new JSONObject(response);
            jsonObject.put("city", cityForecast.getLocation());

            jsonObject.put("forecast", new JSONArray(cityForecast.getForecast()));
            System.out.println(jsonObject);
            return jsonObject;
        }
        else {
            return  null;
        }
    }

    public Weather getWeatherInfo(String city) {
        String response = getRequests.getWeatherRequests(city);
        System.out.println("getWeatherInfoStart");
        System.out.println(response);
        System.out.println("getWeatherInfoEnd");

        String forecastResponse = getRequests.getForecastForWeather(city);
        System.out.println(forecastResponse);
        if (response.length() != 0 && forecastResponse.length() != 0) {
            JSONObject fullInfo = new JSONObject(response);
            String fullInfoToString = fullInfo.toString();
            System.out.println(fullInfo);
            JSONObject main = (JSONObject) fullInfo.get("main");
            Double temperature = (Double) main.get("temp");
            JSONArray weather = (JSONArray) fullInfo.get("weather");
            JSONObject weatherObject = weather.getJSONObject(0);
            JSONObject jsonForecastObject = new JSONObject(forecastResponse);
            JSONArray forecastObjList = (JSONArray) jsonForecastObject.get("list");
            String forecastList = forecastObjList.toString();

            System.out.println("forecastList");
            System.out.println(forecastList);

            String description = (String) weatherObject.get("description");
            String icon = (String) weatherObject.get("icon");
            Integer idCounter = (Integer) fullInfo.get("id");
            Weather newWeather = new Weather(idCounter, city, description, temperature, icon, forecastList, fullInfoToString);

            Boolean canAdd = true;
            for (Weather getWeather : weatherList) {
                if (getWeather.getId().equals(idCounter)) {
                    canAdd = false;
                    break;
                }
            }

            if (canAdd == true) {
                weatherList.add(newWeather);
            }

            int maxSize = weatherList.size();
            if (maxSize == 6 && canAdd == true) {
                weatherList.remove(0);
            }
            return newWeather;
        }
        else {
            return null;
        }
    }

}