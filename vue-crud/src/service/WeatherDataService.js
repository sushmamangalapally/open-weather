import axios from "axios";
const API_URL = "http://localhost:8080";

class WeatherDataService {
  retrieveAllWeather() {
    return axios.get(`${API_URL}`);
  }
  searchWeather(city) {
    // eslint-disable-next-line no-console
    console.log(city);
    return axios.get(`${API_URL}/${city}`);
  }
}

export default new WeatherDataService();
