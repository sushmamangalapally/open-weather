package com.showpad.springboot.crud.springcrud.weather;

public class Weather {
    private Integer id;
    public String location;
    public String description;
    public Double temperature;
    public String icon;

    public Weather() {

    }

    public Weather(Integer id, String location) {
        super();
        this.id = id;
        this.location = location;
    }

    public Weather(Integer id, String location, String description, Double temperature, String icon) {
        super();
        this.id = id;
        this.location = location;
        this.description = description;
        this.temperature = temperature;
        this.icon = icon;
    }
    // getters and setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}