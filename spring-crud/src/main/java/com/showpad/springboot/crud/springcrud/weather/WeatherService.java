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
        String response = getRequests.getWeatherRequests("San Francisco");        
        if (response.length() != 0) {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject main = (JSONObject) jsonObject.get("main");
            Double temperature = (Double) main.get("temp");
            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = weather.getJSONObject(0);

            String description = (String) weatherObject.get("description");
            String icon = (String) weatherObject.get("icon");
            Integer idCounter = (Integer) jsonObject.get("id");
            Weather sanFrancisco = new Weather(idCounter, "San Francisco", description, temperature, icon);
            Boolean canAdd = true;
            for (Weather getWeather : weatherList) {
                // String response = getRequests.getWeatherRequests(weather.getLocation());
                // JSONObject cityObject = new JSONObject(response);
                if (getWeather.getId() == idCounter) {
                    canAdd = false;
                }
            }
            if (canAdd == true) {
                weatherList.add(sanFrancisco);
                cityList.add("san%20francisco");
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
        List<Integer> cityIdsList = new ArrayList<>();
        for (Weather weather : weatherList) {
            cityIdsList.add(weather.getId());
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

        String response = getRequests.getAllWeatherRequests(idsParam);
        // don't have to use getAllWeatherRequests function
        // can use getId loop in cityIdsList
        // and reset everytime with setDescription, setTemperature, and setIcon
        if (response.length() != 0) {
            JSONObject jsonObject = new JSONObject(response);
            System.out.println(jsonObject);
            return jsonObject;
        }
        else {
            return  null;
        }
    }

    public Weather getWeatherInfo(String city) {
        String response = getRequests.getWeatherRequests(city);
        if (response.length() != 0) {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject main = (JSONObject) jsonObject.get("main");
            Double temperature = (Double) main.get("temp");
            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = weather.getJSONObject(0);

            String description = (String) weatherObject.get("description");
            String icon = (String) weatherObject.get("icon");
            Integer idCounter = (Integer) jsonObject.get("id");
            Weather newWeather = new Weather(idCounter, city, description, temperature, icon);

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
            return  newWeather;
        }
        else {
            return null;
        }
    }

}