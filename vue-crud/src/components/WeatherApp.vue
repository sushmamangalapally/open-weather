/* eslint-disable no-console */
<template>
<div>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li v-for="cityWeather in weatherList" v-bind:key="cityWeather.id" class="nav-item">
                    <span v-on:click="goLink(cityWeather.name)" class="city-info">
                        {{cityWeather.name}} <img class="card-img" :src="'http://openweathermap.org/img/w/'+cityWeather.weather[0].icon+'.png'" alt="Weather icon" style="width: 60px"> {{cityWeather.main.temp}}
                    </span>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" @submit="validateAndSearch">
                <input class="form-control mr-sm-2" type="search" placeholder="Search Location" aria-label="Search" v-model="location">
                <button class="btn btn-outline my-2 my-sm-0" type="submit">Search</button>
            </form>
        <!-- <button id = "find-me" @click="geoFindMe">Show my location</button><br/> -->
<!-- <p id = "status"></p>
<a id = "map-link" target="_blank"></a> -->

        </div>
    </nav>
    <div class="container-fluid">
        <div v-if="getData != ''">
            <Weather :weatherSoloObj="getData" :averages="averages" :info="info"></Weather>
        </div>
        <div v-else>
            <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <h3>Not a valid city!</h3>
            </div>
        </div>
    </div>
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
                forecast: []
            },
            searchHistory: {
                city: []
            }
        };
    },
    methods: {
//     success(position) {
//     const latitude  = position.coords.latitude;
//     const longitude = position.coords.longitude;
//     fetch(`https://maps.googleapis.com/maps/api/geocode/json?latlng=${latitude},${longitude}&key=AIzaSyAJftp0WQ5M0EYPhGCzVBwoiHtA9v8wZLc`)
//       .then(response => {
//           // eslint-disable-next-line no-console
//           console.log('success!')
//           // eslint-disable-next-line no-console
//           console.log(response)
//         //   console.log(response)
//       if (response.status !== 200) {
//         // eslint-disable-next-line no-console
//         console.log('Looks like there was a problem. Status Code: ' +
//           response.status);
//         return;
//       }

//       // Examine the text in the response
//       response.json().then(function(data) {
//         // eslint-disable-next-line no-console
//         console.log(this);
//         // eslint-disable-next-line no-console
//         console.log(data.results[0]["address_components"][3]);
//     //    this.refreshWeatherLocation('Miami')

//             this.location = data.results[0]["address_components"][3]["long_name"];
//             return data.results[0]["address_components"][3]["long_name"]
//       });
//     }
//     )
    

    

//     // status.textContent = '';
//   },
//         geoFindMe() {


 

//   if(!navigator.geolocation) {
//     // status.textContent = 'Geolocation is not supported by your browser';
//   } else {
//     // status.textContent = 'Locating…';
//     let loc = navigator.geolocation.getCurrentPosition(this.success, () => alert('No location found!'));
//     // eslint-disable-next-line no-console
//     console.log(loc)
//   }

// },
        weather() {

    let getWe = this.getData.forecast;
    getWe.forEach((el, i) => {
        this.averages[i] = [el['dt']*1000, el['main']['temp']]
    })

  // eslint-disable-next-line no-console
  console.log("averages")

  // eslint-disable-next-line no-console
  console.log(this.averages)
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
    color: 'violet',
    marker: {
      fillColor: 'white',
      lineWidth: 2,
      lineColor: 'purple'
    }
  }
  // 995
  ]
});

    }

        ,
        refreshNav() {
            WeatherDataService.retrieveAllWeather() //HARDCODED
                .then(response => {
                    // eslint-disable-next-line no-console
                    console.log("refreshNav");
                    // eslint-disable-next-line no-console
                    console.log(response.data);
                    let list = response.data.list;
                    this.weatherList = list.reverse();
                });
        },
        refreshWeatherLocation() {
                // if (this.location == "") {
                //     this.location = this.$route.path.slice(1);
                // }
                // eslint-disable-next-line no-console
                console.log(this.$route.path)
                // eslint-disable-next-line no-console
                console.log(this.$route.path)
                this.location = this.$route.params.location;
                WeatherDataService.searchWeather(this.location) //HARDCODED
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log('searchWeather');
                        // eslint-disable-next-line no-console
                        console.log(response);
                        // eslint-disable-next-line no-console
                        console.log(response.data);
                        // response.data.forecastResponse = JSON.parse(response.data.forecastResponse);
                        this.getData = response.data;
                        this.getData = response.data;
                        let info = JSON.parse(response.data.fullInfo);
                        // eslint-disable-next-line no-console
                        console.log("BOOO")
                        // eslint-disable-next-line no-console
                        console.log(info)
                        this.getData = {
                            "location": response.data.location,
                            "locationName": info.name,
                            "description": response.data.description,
                            "temperature": response.data.temperature,
                            "icon": response.data.icon,
                            "forecast": response.data !== "" ? JSON.parse(response.data.forecast) : '',
                            "date": null,
                            "windSpeed": info.wind.speed,
                            "feelsLike": info.main.feels_like,
                            "fullInfo": info
                        };
                        // eslint-disable-next-line no-console
                        console.log(this.getData)
                        if (response.data !== "") {
                            this.weather();
                        }
                        else {

                            this.getData = '';
                            this.refreshNav();
                        }
                        // this.refreshNav();
                    });

        },
        refreshWeather() {
            if (this.$route.path == "/") {
                WeatherDataService.retrieveAllWeather() //HARDCODED
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log("response");
                        // eslint-disable-next-line no-console
                        console.log(response);

                        this.refreshNav();
                        let list = response.data.list;
                        // eslint-disable-next-line no-console
                        console.log("refreshWeather");
                        // eslint-disable-next-line no-console
                        console.log(list);

                        let lastRecentParseInfo = list[list.length - 1];

                        this.weatherList = list.slice(0, list.length - 1).reverse();
                        // eslint-disable-next-line no-console
                        console.log("lastRecentParseInfo")
                        // eslint-disable-next-line no-console
                        console.log(lastRecentParseInfo)
                        if (response.data !== "") {
                            let info = {wind: {speed: ""}, main: {feels_like: ""}};
                            if (response.data.fullInfo) {
                                info = JSON.parse(response.data.fullInfo);
                            }
                            
            // this.refreshWeatherLocation();
                        this.getData = {
                            "location": lastRecentParseInfo.name,
                            "locationName": info.name == undefined ? lastRecentParseInfo.name : info.name,
                            "description": lastRecentParseInfo.weather[0].description,
                            "temperature": lastRecentParseInfo.main.temp,
                            "icon": lastRecentParseInfo.weather[0].icon,
                            "forecast": response.data.forecast,
                            "windSpeed": info.wind.speed === "" ? lastRecentParseInfo.wind.speed : info.wind.speed,
                            "feelsLike": info.main.feels_like == "" ? lastRecentParseInfo.main.feels_like : info.main.feels_like,
                            "fullInfo": info
                        };
                        }
                        // eslint-disable-next-line no-console
                        console.log("WATAHHHHH");
                        // eslint-disable-next-line no-console
                        console.log(this.getData);
                        // eslint-disable-next-line no-console
                        console.log(this.weatherList);

                        this.weather() 
                    });
            }
            // eslint-disable-next-line no-console
                        console.log("handshake");
                        // eslint-disable-next-line no-console
                        console.log(this.getData);
                        // eslint-disable-next-line no-console
                        console.log(this.weatherList);
                        // this.info = {};
        },
        goLink(name) {
            // eslint-disable-next-line no-console
            console.log(name);
            WeatherDataService.searchWeather(name).then((response) => {
                this.$router.push(`/${name}`);
                this.getData = response.data;
                // var historyObj = JSON.parse(localStorage.getItem('cities'));
                // if (!(historyObj.includes(name))) {
                //     this.addHistory.push(name);
                // }
            });

            this.refreshNav();
        },

        validateAndSearch(e) {
            e.preventDefault();
            // eslint-disable-next-line no-console
            console.log({
                location: this.location,
            });

            if (this.location) {
                WeatherDataService.searchWeather(this.location).then((response) => {
                    this.$router.push(`/${this.location}`);
                    // eslint-disable-next-line no-console
                    console.log("validateAndSearch")
                    // eslint-disable-next-line no-console
                    console.log(response)
                    this.getData = response.data;
                    // var historyObj = JSON.parse(localStorage.getItem('cities'));
                    // if (!(historyObj.includes(name))) {
                    //     this.addHistory(name);
                    // }
                });
            }
            this.refreshNav();
        },
        onLoad() {
            if (localStorage.getItem('cities')) {
                this.searchHistory = JSON.parse(localStorage.getItem('cities'));
            }
            // eslint-disable-next-line no-console
            console.log(this.searchHistory);
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
        // eslint-disable-next-line no-console
        console.log(this.$route);
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
    created() {
        /*
        where you would want to make your API calls and set your data because it is the earliest stage in the Vue
        */
        // eslint-disable-next-line no-console
        console.log('dfdfdfd')
        // this.refreshWeather();
        // if (this.$route.params.location) {
        //     this.refreshWeatherLocation();
        // }
        // this.$nextTick(function () { // could also use mounted after the DOM elements has been mounted
        //     // DOM is now updated
        //     // `this` is bound to the current instance
        //     // eslint-disable-next-line no-console
        //     console.log(this.getData);

        //     // this.onLoad();
            // // setTimeout(() => {
            //     this.refreshWeather();
            // // });
        // })
    },
    mounted() {
        // this.refreshWeatherLocation();
        this.refreshWeather();
        if (this.$route.params.location) {
            this.refreshWeatherLocation();
        }
        // eslint-disable-next-line no-console
        console.log(this.weatherList);
        
        // eslint-disable-next-line no-console
        console.log("this.getData")
        // eslint-disable-next-line no-console
        console.log(this.getData);

    }
}
</script>
<style scoped>
</style>