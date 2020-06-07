<template>
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="card bg-light mb-3" style="max-width: 18rem;">
                <div class="card-header">
                    {{weatherSoloObj.locationName}}
                </div>

                <img class="card-img" :src="'http://openweathermap.org/img/w/'+weatherSoloObj.icon+'.png'" alt="Weather icon" style="max-width: 45%;">
                <div class="card-body">
                    <h5 class="card-title">{{weatherSoloObj.temperature}}<span>°F</span></h5>
                    <h6 class="card-text">Feels like <b>{{weatherSoloObj.feelsLike}}<span>°F</span></b></h6>
                    <p class="card-text">{{weatherSoloObj.description.charAt(0).toUpperCase() + weatherSoloObj.description.slice(1) }}</p>
                    <h6 class="card-text">Wind Speed: <b>{{weatherSoloObj.windSpeed }} mph</b></h6>
                </div>
            </div>
        </nav>
        <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <figure class="highcharts-figure">
                <div id="container"></div>
            </figure>
            <div class="card-group">
                <div v-for="foreWeather in filteredForecast" v-bind:key="foreWeather.dt" class="card">
                    <div class="card-body">
                        <img :src="'http://openweathermap.org/img/w/'+foreWeather.weather[0].icon+'.png'" class="card-img-top" :alt="foreWeather.weather[0].main">
                        <h6 class="card-title">{{new Date(foreWeather.dt_txt).toLocaleDateString("en-US", options)}}</h6>
                        <p class="card-text">{{foreWeather.main.temp}}<span>°F</span></p>
                        <p class="card-text">{{foreWeather.weather[0].description.charAt(0).toUpperCase()+foreWeather.weather[0].description.slice(1) }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Weather",
    props: {
        weatherSoloObj: {
            type: Object,
            default: function () {
                return {
                    location: "",
                    description: "",
                    temperature: 0.0,
                    iconLink: "",
                    date: null
                }
            }
        },
        averages: {
            type: Array,
            default: function () {
                return []
            }
        },
        info: {
            type: Object,
            default: function () {
                return {}
            }
        },
    },
    data() {
        return {
            weatherForecast: [],
            filteredAverage: [],
            date: null,
            options: { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' }
        }
    },
    computed: {
        filteredForecast() {
            return this.weatherSoloObj.forecast.filter((value, index) =>  index % 8 == 0)
        },
    }
}
</script>

<style scoped>
    .line {
      fill: none;
      stroke-width: 1.5px;
    }
    h5.card-title {
        font-size: 190%;
    }
    .card-title > span {
        font-size: medium;
        position: relative;
        top: -1em;
    }

    div.card {
        border: 1px solid lightgrey;
    }
</style>
