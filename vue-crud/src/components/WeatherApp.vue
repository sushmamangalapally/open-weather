/* eslint-disable no-console */
/* eslint-disable no-console */
<template>
    <div class="container-fluid">

        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li v-for="cityWeather in weatherList" v-bind:key="cityWeather.id" class="nav-item">
                <span v-on:click="goLink(cityWeather.name)" class="city-info"> {{cityWeather.name}} <img class="card-img" :src="'http://openweathermap.org/img/w/'+cityWeather.weather[0].icon+'.png'" alt="Weather icon" style="width: 60px"> {{cityWeather.main.temp}}</span>
       
            </li>
        </ul>
            <form class="form-inline my-2 my-lg-0" @submit="validateAndSearch">
                <input class="form-control mr-sm-2" type="search" placeholder="Search Location" aria-label="Search" v-model="location" >
                <button class="btn btn-outline my-2 my-sm-0" type="submit">Search</button>
                
            </form>
            <div v-if="errors.length">
                {{this.errors.slice(-1)}}
                </div>
            </div>
        </nav>

                <div
                    class="alert alert-warning"
                    v-bind:key="index"
                    v-for="(error, index) in errors"
                ></div>
            <div v-if="getData != ''">
                <Weather :weatherSoloObj="getData"></Weather>
            </div>
            <div v-else>
                <h3>Not a valid city!</h3>
            </div>

        <!-- <Weather></Weather> -->
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
    // props: {
    //     weatherSoloObj: {
    //         type: Object,
    //         default: {
    //             location: "",
                
    //         }
    //     }
    // },
 
    data() {
        return {
            displayLocation: "",
            location: "",
            description: "",
            temperature: 0.0,
            iconLink: "",
            errors: [],
            weatherList: [],
            link: "",
            getData: {
                location: "",
                icon: "",
                temperature: 0,
                description: ""
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
                        //.slice(0, list.length-1).reverse();
                        // eslint-disable-next-line no-console
                        console.log(this.weatherList);
                        for (let weatherObj of this.weatherList) {
                            // eslint-disable-next-line no-console
                            console.log(weatherObj);
                        }
                    });

        },
        refreshCourses() {
            // eslint-disable-next-line no-console
            console.log(this)
            if (this.$route.path == "/") {
                WeatherDataService.retrieveAllWeather() //HARDCODED
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log(response);
                        // eslint-disable-next-line no-console
                        console.log(response.data);
                        let list = response.data.list;
                        // eslint-disable-next-line no-console
                        console.log(list);
                        let lastRecentParseInfo = list[list.length-1];
                        // eslint-disable-next-line no-console
                        console.log(lastRecentParseInfo);
                        this.displayLocation = lastRecentParseInfo.name;
                        this.description = lastRecentParseInfo.weather[0].description;
                        this.temperature = lastRecentParseInfo.main.temp;
                        this.iconLink  = "http://openweathermap.org/img/w/"+lastRecentParseInfo.weather[0].icon+".png";
                        this.weatherList = list.slice(0, list.length-1).reverse();
                        this.getData = {
                            "location": lastRecentParseInfo.name,
                            "description": lastRecentParseInfo.weather[0].description,
                            "temperature": lastRecentParseInfo.main.temp,
                            "icon": lastRecentParseInfo.weather[0].icon
                        };
                        // eslint-disable-next-line no-console
                        console.log(this.weatherList);
                        for (let weatherObj of this.weatherList) {
                            // eslint-disable-next-line no-console
                            console.log(weatherObj);
                        }
                        return 
                    });
            }
            else {
                if (this.location == "") {
                    this.location = this.$route.path;
                }
                // eslint-disable-next-line no-console
                console.log(this.$route.path)
                WeatherDataService.searchWeather(this.location) //HARDCODED
                    .then(response => {
                        // eslint-disable-next-line no-console
                        console.log(response.data);
                        // this.weatherSoloObj = response.data;
                        this.getData = response.data;
                        this.displayLocation = response.data.location;
                        this.description = response.data.description;
                        this.temperature = response.data.temperature;
                        this.iconLink  = "http://openweathermap.org/img/w/"+response.data.icon+".png";  
                    });

            }
        },
        goLink(name) {
            // eslint-disable-next-line no-console
            console.log(name);
            // this.$router.push("/"+name);
            // this.$router.push({
            //     name: 'Weather',
            //     params: {
            //         name: name
            //     }
            // })
                WeatherDataService.searchWeather(name).then((response) => {
                    this.$router.push(`/${name}`);
                        this.getData = response.data;
                });
                this.refreshNav();
        },
    
    validateAndSearch(e) {
        e.preventDefault();
        // eslint-disable-next-line no-console
        console.log({
            location: this.location,
        });

        this.errors = [];
        if (!this.location) {
            this.errors.push("Enter valid values");
        }

        if (this.errors.length === 0) {
            // if (this.array.includes(location) === -1) {
                WeatherDataService.searchWeather(this.location).then((response) => {
                    this.$router.push(`/${this.location}`);
                    this.displayLocation = response.data.location;
                    this.description = response.data.description;
                    this.temperature = response.data.temperature;
                    this.iconLink  = "http://openweathermap.org/img/w/"+response.data.icon+".png";
                    this.getData = response.data;
                });

                // this.$router.push("/"+name);
            // } else {
            //     CourseDataService.updateCourse(this.INSTRUCTOR, this.id, {
            //         id: this.id,
            //         description: this.description
            //     }).then(() => {
            //         this.$router.push("/courses");
            //     });
            // }
        }
        this.refreshNav();
   },
    },
    beforeCreate() {
        // eslint-disable-next-line no-console
        console.log(this.$route);
        this.getData = {
            "location": "SF",
            "description": "ln",
            "temperature": 30,
            "icon": "01N"
        };
    },
    created() {
        //  
        // eslint-disable-next-line no-console
        console.log('cry')
        // this.refreshCourses();

      this.$nextTick(function () {
        // DOM is now updated
        // `this` is bound to the current instance
        // eslint-disable-next-line no-console
        console.log(this.getData)
        // eslint-disable-next-line no-console
        console.log('what')
        this.refreshCourses()
        // eslint-disable-next-line no-console
        console.log(this.getData)
      })
    }
}

</script>

<style scoped>

</style>
