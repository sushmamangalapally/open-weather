import axios from "axios";
// const location = "san%20francisco"
const COURSE_API_URL = "http://localhost:8080";
// const INSTRUCTOR_API_URL = `${COURSE_API_URL}/${location}`;

class WeatherDataService {
  retrieveAllWeather() {
    return axios.get(`${COURSE_API_URL}`);
  }
  searchWeather(city) {
    // eslint-disable-next-line no-console
    console.log('executed service')
    // eslint-disable-next-line no-console
    console.log(city)
    return axios.get(`${COURSE_API_URL}/${city}`);
  }
}

export default new WeatherDataService();
