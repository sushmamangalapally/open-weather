package com.showpad.springboot.crud.springcrud.api;

import org.springframework.web.util.UriTemplate;
import java.net.URI;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class getRequests {
    private static final String BASE_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&units=imperial&appid=56d04b44687f1ebdb10d724cdc52fd05";
    private static final String ALL_CITIES_API_URL = "http://api.openweathermap.org/data/2.5/group?id={cityIds}&units=imperial&appid=56d04b44687f1ebdb10d724cdc52fd05";

    static public String getWeatherRequests(String city) {
        try {
            URI uri = new UriTemplate(BASE_WEATHER_API_URL).expand(city);

            URL url = new URL(uri.toString());
            String response = "";
            // try {
                URLConnection request = url.openConnection();
                request.connect();

                BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }

                in.close();
            // } catch (IOException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
                // JSONObject jsonObject = new JSONObject(response);
                return response;
                // System.out.println(response.toString());
                // System.out.println(jsonObject);
                // System.out.println(jsonObject.get("dt"));
                // // Weather Type Icon: Cloudy, Sunny 
                // // Description: light intensity drizzle 
                // // Temperature 
                // JSONObject main = (JSONObject) jsonObject.get("main");
                // Double temperature = (Double) main.get("temp");
                // JSONArray weather = (JSONArray) jsonObject.get("weather");
                // JSONObject weatherObject = weather.getJSONObject(0);
                // // for (int i=0; i < weather.length(); i++) {
                //     System.out.println(weatherObject);
                //     System.out.println(weatherObject.get("icon"));
                // // }

                // String description = (String) weatherObject.get("description");
                // String icon = (String) weatherObject.get("icon");
                // String iconLink = "http://openweathermap.org/img/w/"+icon+".png";
                // Weather newWeather = new Weather(++idCounter, city, description, temperature, icon);
                // weatherList.add(newWeather);
                // return  newWeather;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    static public String getAllWeatherRequests(String citiesIds) {
        try {
            URI uri = new UriTemplate(ALL_CITIES_API_URL).expand(citiesIds);

            System.out.println(citiesIds);

            URL url = new URL(uri.toString());
            String response = "";
            // try {
                URLConnection request = url.openConnection();
                request.connect();

                BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response += inputLine;
                }

                in.close();
            // } catch (IOException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
                // JSONObject jsonObject = new JSONObject(response);
                return response;
                // System.out.println(response.toString());
                // System.out.println(jsonObject);
                // System.out.println(jsonObject.get("dt"));
                // // Weather Type Icon: Cloudy, Sunny 
                // // Description: light intensity drizzle 
                // // Temperature 
                // JSONObject main = (JSONObject) jsonObject.get("main");
                // Double temperature = (Double) main.get("temp");
                // JSONArray weather = (JSONArray) jsonObject.get("weather");
                // JSONObject weatherObject = weather.getJSONObject(0);
                // // for (int i=0; i < weather.length(); i++) {
                //     System.out.println(weatherObject);
                //     System.out.println(weatherObject.get("icon"));
                // // }

                // String description = (String) weatherObject.get("description");
                // String icon = (String) weatherObject.get("icon");
                // String iconLink = "http://openweathermap.org/img/w/"+icon+".png";
                // Weather newWeather = new Weather(++idCounter, city, description, temperature, icon);
                // weatherList.add(newWeather);
                // return  newWeather;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}