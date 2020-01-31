package com.showpad.springboot.crud.springcrud.api;

import org.springframework.web.util.UriTemplate;
import java.net.URI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class getRequests {
    private static final String BASE_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&units=imperial&appid=56d04b44687f1ebdb10d724cdc52fd05";
    private static final String ALL_CITIES_API_URL = "http://api.openweathermap.org/data/2.5/group?id={cityIds}&units=imperial&appid=56d04b44687f1ebdb10d724cdc52fd05";

    static public String getWeatherRequests(String city) {
        URI uri = new UriTemplate(BASE_WEATHER_API_URL).expand(city);
        String weatherResponse = getReponse(uri);
        return weatherResponse;
    }

    static public String getAllWeatherRequests(String citiesIds) {
        URI uri = new UriTemplate(ALL_CITIES_API_URL).expand(citiesIds);

        System.out.println(citiesIds);
        String allCitiesResponse = getReponse(uri);
        return allCitiesResponse;
    }

    static public String getReponse(URI urlString) {
        String response = "";
        try {
            URL url = new URL(urlString.toString());

            URLConnection request = url.openConnection();
            request.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }

            in.close();
            return response;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ""; 
    }
}