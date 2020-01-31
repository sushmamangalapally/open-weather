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
        <Weather :weatherSoloObj="getData"></Weather>
    </div>
    <div v-else>
        <h3>Not a valid city!</h3>
    </div>
</div>
</template>

<script>
import WeatherDataService from '../service/WeatherDataService';
import Weather from './Weather';

export default {
    name: "WeatherApp",
    components: {
        Weather
    },
    data() {
        return {
            location: "",
            weatherList: [],
            link: "",
            getData: {
                location: "",
                icon: "",
                temperature: 0,
                description: ""
            },
            searchHistory: {
                city: []
            }
        };
    },
    methods: {
        refreshNav() {
            WeatherDataService.retrieveAllWeather() //HARDCODED
                .then(response => {
                    // eslint-disable-next-line no-console
                    console.log(response.data);
                    let list = response.data.list;
                    this.weatherList = list.reverse();
                });
        },
        refreshWeather() {
            if (this.$route.path == "/") {
                WeatherDataService.retrieveAllWeather() //HARDCODED
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log(response);

                        let list = response.data.list;

                        let lastRecentParseInfo = list[list.length - 1];

                        this.weatherList = list.slice(0, list.length - 1).reverse();
                        this.getData = {
                            "location": lastRecentParseInfo.name,
                            "description": lastRecentParseInfo.weather[0].description,
                            "temperature": lastRecentParseInfo.main.temp,
                            "icon": lastRecentParseInfo.weather[0].icon
                        };
                        // eslint-disable-next-line no-console
                        console.log(this.weatherList);
                    });
            } else {
                if (this.location == "") {
                    this.location = this.$route.path.slice(1);
                }
                // eslint-disable-next-line no-console
                console.log(this.$route.path)
                WeatherDataService.searchWeather(this.location) //HARDCODED
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log(response.data);
                        this.getData = response.data;
                    });
            }
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
        },
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
        this.$nextTick(function () { // could also use mounted after the DOM elements has been mounted
            // DOM is now updated
            // `this` is bound to the current instance
            // eslint-disable-next-line no-console
            console.log(this.getData);

            // this.onLoad();
            setTimeout(() => {
                this.refreshWeather();
            });
        })
    }
}
</script>

<style scoped>

</style>
