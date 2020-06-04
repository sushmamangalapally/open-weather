<template>
<div class="container-fluid">
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
        </div>
    </nav>

    <div v-if="getData != ''">
        <Weather :weatherSoloObj="getData" :averages="averages"></Weather>
    </div>
    <div v-else>
        <h3>Not a valid city!</h3>
    </div>
    <div id="ford3"></div>
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
        weather() {

    let getWe = this.getData.forecast;
    getWe.forEach((el, i) => {
        this.averages[i] = [el['dt']*1000, el['main']['temp']]
    })

  // eslint-disable-next-line no-console
  console.log("averages")

  // eslint-disable-next-line no-console
  console.log(this.averages)


Highcharts.chart('container', {

  title: {
    text: 'Forecast Timeline for Next 5 Days Per 3 Hour'
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
                        console.log(response.data);
                        // response.data.forecastResponse = JSON.parse(response.data.forecastResponse);
                        this.getData = response.data;
                        this.getData = response.data;
                        this.getData = {
                            "location": response.data.location,
                            "description": response.data.description,
                            "temperature": response.data.temperature,
                            "icon": response.data.icon,
                            "forecast": JSON.parse(response.data.forecast)
                        };
                        this.weather();
                        this.refreshNav();
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

                        let list = response.data.list;

                        let lastRecentParseInfo = list[list.length - 1];

                        this.weatherList = list.slice(0, list.length - 1).reverse();
                        // eslint-disable-next-line no-console
                        console.log("lastRecentParseInfo")
                        // eslint-disable-next-line no-console
                        console.log(lastRecentParseInfo)
                        this.getData = {
                            "location": lastRecentParseInfo.name,
                            "description": lastRecentParseInfo.weather[0].description,
                            "temperature": lastRecentParseInfo.main.temp,
                            "icon": lastRecentParseInfo.weather[0].icon,
                            "forecast": response.data.forecast
                        };
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
            "icon": ""
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