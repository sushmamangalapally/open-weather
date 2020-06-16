<template>
    <div class="grid-container">
        <header class="header">
            <div class="navbar">
                <div v-for="cityWeather in weatherList" v-bind:key="cityWeather.id" class="nav-item">
                    <span v-on:click="goLink(cityWeather.name)" class="city-info">
                        {{cityWeather.name}} <img class="card-img" :src="'http://openweathermap.org/img/w/'+cityWeather.weather[0].icon+'.png'" alt="Weather icon" style="width: 60px"> {{cityWeather.main.temp}}
                    </span>
                </div>
            </div>
            <form class="form-inline my-2 my-lg-0 header__search" @submit="validateAndSearch">
                <input class="form-control mr-sm-2" type="search" placeholder="Search Location" aria-label="Search" v-model="location">
                <button class="btn btn-outline my-2 my-sm-0" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
            </form>
        </header>
        <template v-if="getData != ''">
            <Weather :weatherSoloObj="getData" :averages="averages" :info="info"></Weather>
        </template>
        <template v-else>
            <aside class="sidenav"></aside>
            <main class="main"><h3>Not a valid city!</h3></main>
        </template>
        <footer class="footer">
            <div class="footer__copyright">&copy; 2020</div>
            <div class="footer__signature">Made by Sushma</div>
        </footer>
    </div>
</template>

<script>
import WeatherDataService from '../service/WeatherDataService';
import Weather from './Weather';
var Highcharts = require('highcharts');

export default {
    name: "WeatherApp",
    components: {
        Weather
    },
      watch: {
        $route(to, from) {
        // eslint-disable-next-line no-console
        console.log("to", to);
        // eslint-disable-next-line no-console
        console.log("from", from);
        this.refreshWeatherLocation();
        // react to route changes...
        }
  },
    data() {
        return {
            location: "",
            weatherList: [],
            link: "",
            averages: [],
            info: {},
            "windSpeed": 0,
            getData: {
                location: "",
                icon: "",
                temperature: 0,
                description: "",
                forecast: [],
                time: ""
            },
            searchHistory: {
                city: []
            }
        };
    },
    methods: {
        weather() {
            let getWe = this.getData.forecast;
            getWe.forEach((el, i) => {
                this.averages[i] = [el['dt']*1000, el['main']['temp']]
            })

            // eslint-disable-next-line no-console
            // console.log(this.averages)
            this.refreshNav();

            Highcharts.chart('container', {
                title: {
                    text: 'Forecast Timeline for Next 5 Days For Every 3 Hour'
                },

                xAxis: {
                    type: 'datetime',
                    accessibility: {
                    rangeDescription: 'Range for the Next 5 Days'
                    }
                },

                yAxis: {
                    title: {
                    text: 'Temperature'
                    }
                },

                tooltip: {
                    crosshairs: true,
                    shared: true,
                    valueSuffix: '°F'
                },

                series: [{
                    name: 'Temperature',
                    data: this.averages,
                    zIndex: 1,
                    color: 'forestgreen',
                    marker: {
                    fillColor: 'white',
                    lineWidth: 2,
                    lineColor: '#006666'
                    }
                }
                ]
            });

        },
        refreshNav() {
            WeatherDataService.retrieveAllWeather() //HARDCODED
            .then(response => {
                // eslint-disable-next-line no-console
                // console.log("refreshNav");
                // eslint-disable-next-line no-console
                // console.log(response.data);
                let list = response.data.list;
                this.weatherList = list.reverse();
            });
        },
        toTimeZone(unix_timestamp) {
            var date = new Date(unix_timestamp * 1000);
            // Hours part from the timestamp
            // var hours = date.getHours();
            // // Minutes part from the timestamp
            // var minutes = "0" + date.getMinutes();
            // // Seconds part from the timestamp
            // var seconds = "0" + date.getSeconds();

            // Will display time in 10:30:23 format
            // var formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
            return date.toDateString();
        },
        refreshWeatherLocation() {
            // eslint-disable-next-line no-console
            // console.log(this.$route.path)
            // eslint-disable-next-line no-console
            // console.log(this.$route.path)
            this.location = this.$route.params.location;
            WeatherDataService.searchWeather(this.location) //HARDCODED
            .then(response => {
                // eslint-disable-next-line no-console
                // console.log('searchWeather');
                // eslint-disable-next-line no-console
                // console.log(response);
                // eslint-disable-next-line no-console
                // console.log(response.data);
                this.getData = response.data;
                let info = JSON.parse(response.data.fullInfo);
                // eslint-disable-next-line no-console
                // console.log(info);

                this.getData = {
                    "location": response.data.location,
                    "locationName": info.name,
                    "description": response.data.description,
                    "temperature": response.data.temperature,
                    "icon": response.data.icon,
                    "forecast": response.data !== "" ? JSON.parse(response.data.forecast) : '',
                    "date": null,
                    "windSpeed": info.wind.speed,
                    "cloudiness": info.clouds.all,
                    "windDir": info.wind.deg,
                    "minTemp": info.main.temp_min,
                    "maxTemp": info.main.temp_max,
                    "feelsLike": info.main.feels_like,
                    "fullInfo": info,
                    "humidity": info.main.humidity,
                    "visibility": info.visibility*0.000621371,
                    "pressure": info.main.pressure,
                    "time": this.toTimeZone(info.dt)
                };

                if (response.data !== "") {
                    this.weather();
                }
                else {
                    this.getData = '';
                    this.refreshNav();
                }
            });
        },
        refreshWeather() {
            if (this.$route.path == "/") {
                WeatherDataService.retrieveAllWeather() //HARDCODED
                .then(response => {
                    // eslint-disable-next-line no-console
                    // console.log("response");
                    // eslint-disable-next-line no-console
                    // console.log(response);

                    this.refreshNav();
                    let list = response.data.list;
                    // eslint-disable-next-line no-console
                    // console.log("refreshWeather");
                    // eslint-disable-next-line no-console
                    // console.log(list);

                    let lastRecentParseInfo = list[list.length - 1];

                    this.weatherList = list.slice(0, list.length - 1).reverse();
                    // eslint-disable-next-line no-console
                    // console.log("lastRecentParseInfo")
                    // eslint-disable-next-line no-console
                    // console.log(lastRecentParseInfo)
                    if (response.data !== "") {
                        let info = {wind: {speed: ""}, main: {feels_like: ""}};
                        if (response.data.fullInfo) {
                            info = JSON.parse(response.data.fullInfo);
                        }
                        
                    this.getData = {
                        "location": lastRecentParseInfo.name,
                        "locationName": info.name == undefined ? lastRecentParseInfo.name : info.name,
                        "description": lastRecentParseInfo.weather[0].description,
                        "temperature": lastRecentParseInfo.main.temp,
                        "icon": lastRecentParseInfo.weather[0].icon,
                        "forecast": response.data.forecast,
                        "windSpeed": info.wind.speed === "" ? lastRecentParseInfo.wind.speed : info.wind.speed,
                        "feelsLike": info.main.feels_like == "" ? lastRecentParseInfo.main.feels_like : info.main.feels_like,
                        "fullInfo": info,
                        "cloudiness": info.clouds.all === undefined ? lastRecentParseInfo.clouds.all : info.clouds.all,
                        "windDir": info.wind.deg === undefined ? lastRecentParseInfo.wind.deg : info.wind.deg,
                        "minTemp": info.main.temp_min === undefined ? lastRecentParseInfo.main.temp_min : info.main.temp_min,
                        "maxTemp": info.main.temp_max === undefined ? lastRecentParseInfo.main.temp_max : info.main.temp_max,
                        "humidity": info.main.humidity === undefined ? lastRecentParseInfo.main.humidity : info.main.humidity,
                        "visibility": info.visibility === undefined ? lastRecentParseInfo.visibility : info.visibility*0.000621371,
                        "pressure": info.main.pressure === undefined ? lastRecentParseInfo.main.pressure : info.main.pressure,
                        time: info.dt === undefined ? this.toTimeZone(lastRecentParseInfo.dt) : this.toTimeZone(info.dt)
                    };
                    }
                    // eslint-disable-next-line no-console
                    // console.log(this.getData);
                    // eslint-disable-next-line no-console
                    // console.log(this.weatherList);

                    this.weather() 
                });
            }
        },
        goLink(name) {
            // eslint-disable-next-line no-console
            // console.log(name);
            WeatherDataService.searchWeather(name).then((response) => {
                this.$router.push(`/${name}`);
                this.getData = response.data;
            });

            this.refreshNav();
        },

        validateAndSearch(e) {
            e.preventDefault();
            // eslint-disable-next-line no-console
            // console.log({
            //     location: this.location,
            // });

            if (this.location) {
                WeatherDataService.searchWeather(this.location).then((response) => {
                    this.$router.push(`/${this.location}`);
                    // eslint-disable-next-line no-console
                    // console.log("validateAndSearch")
                    // eslint-disable-next-line no-console
                    // console.log(response)
                    this.getData = response.data;
                });
            }
            this.refreshNav();
        },
        onLoad() {
            if (localStorage.getItem('cities')) {
                this.searchHistory = JSON.parse(localStorage.getItem('cities'));
            }
            // eslint-disable-next-line no-console
            // console.log(this.searchHistory);
        },
        addHistory(dataToSave) {
            if (localStorage.getItem('cities')) {
                var historyObj = JSON.parse(localStorage.getItem('cities'));
            }
            historyObj.push(dataToSave);
            localStorage.setItem('cities', JSON.stringify(historyObj));
        }
    },
    beforeCreate() {
        this.getData = {
            "location": "",
            "description": "",
            "temperature": 0,
            "icon": "",
            "feelsLike": 0,
            fullInfo: {},
            "locationName": ''
        };
    },
    mounted() {
        this.refreshWeather();
        if (this.$route.params.location) {
            this.refreshWeatherLocation();
        }
        // eslint-disable-next-line no-console
        // console.log(this.weatherList);
        
        // eslint-disable-next-line no-console
        // console.log("this.getData")
        // eslint-disable-next-line no-console
        // console.log(this.getData);
    }
}
</script>
<style scoped>
</style>