package com.showpad.springboot.crud.springcrud.weather;
import java.util.*;
import org.springframework.stereotype.Service;
import org.json.*;
import com.showpad.springboot.crud.springcrud.api.getRequests;

@Service
public class WeatherService {

    private static List<Weather> weatherList = new ArrayList<>();
    private static List<String> cityList = new ArrayList<>();
    // private static long idCounter = 0;

    static {
        // Weather sanFrancisco = new Weather(++idCounter, "San Francisco");
        String response = getRequests.getWeatherRequests("San Francisco");        
        if (response.length() != 0) {
            JSONObject jsonObject = new JSONObject(response);
            System.out.println(response.toString());
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("dt"));
            // Weather Type Icon: Cloudy, Sunny 
            // Description: light intensity drizzle 
            // Temperature 
            JSONObject main = (JSONObject) jsonObject.get("main");
            Double temperature = (Double) main.get("temp");
            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = weather.getJSONObject(0);
            // for (int i=0; i < weather.length(); i++) {
                System.out.println(weatherObject);
                System.out.println(weatherObject.get("icon"));
            // }

            String description = (String) weatherObject.get("description");
            String icon = (String) weatherObject.get("icon");
            String iconLink = "http://openweathermap.org/img/w/"+icon+".png";
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
            // String response = getRequests.getWeatherRequests(weather.getLocation());
            // JSONObject cityObject = new JSONObject(response);
            cityIdsList.add(weather.getId());
        }
        String idsParam = "";
        for (int counter = 0; counter < cityIdsList.size(); counter++) { 		      
            idsParam += Integer.toString(cityIdsList.get(counter));
            if (counter != (cityIdsList.size()-1)) {
                idsParam += ",";
            }		
        } 
        String response = getRequests.getAllWeatherRequests(idsParam);
        if (response.length() != 0) {
            JSONObject jsonObject = new JSONObject(response);
            System.out.println(response.toString());
            System.out.println(jsonObject);
            System.out.println("im sure ill find it");
            return jsonObject;
        }
        else {
            return  null;
        }
    }

    public Weather getWeatherInfo(String city) {
        System.out.println(city);
        String response = getRequests.getWeatherRequests(city);
        if (response.length() != 0) {
            JSONObject jsonObject = new JSONObject(response);
            System.out.println(response.toString());
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("dt"));
            // Weather Type Icon: Cloudy, Sunny 
            // Description: light intensity drizzle 
            // Temperature 
            JSONObject main = (JSONObject) jsonObject.get("main");
            Double temperature = (Double) main.get("temp");
            JSONArray weather = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = weather.getJSONObject(0);
            // for (int i=0; i < weather.length(); i++) {
                System.out.println(weatherObject);
                System.out.println(weatherObject.get("icon"));
            // }

            String description = (String) weatherObject.get("description");
            String icon = (String) weatherObject.get("icon");
            String iconLink = "http://openweathermap.org/img/w/"+icon+".png";
            Integer idCounter = (Integer) jsonObject.get("id");
            System.out.println("idCounter");
            System.out.println(idCounter);
            Weather newWeather = new Weather(idCounter, city, description, temperature, icon);
            // weatherList.add(newWeather);

            Boolean canAdd = true;
            System.out.println("weatherList"+weatherList);
            for (Weather getWeather : weatherList) {
                // String response = getRequests.getWeatherRequests(weather.getLocation());
                // JSONObject cityObject = new JSONObject(response);
                System.out.println(getWeather);
                System.out.println(getWeather.getId());
                System.out.println(idCounter);
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

            System.out.println(canAdd);
            System.out.println(newWeather);
            return  newWeather;
        }
        else {
            return  null;
        }
    }

}