<template>
    <fragment>
        <aside class="sidenav">
            <div class="bg-light mb-3" style="max-width: 18rem;">
                <div class="side-card-header">
                    {{weatherSoloObj.locationName}}
                    <h3 class="side-card-text">
                    {{weatherSoloObj.time}}
                    </h3>
                </div>
                <img class="side-card-img" :src="'http://openweathermap.org/img/w/'+weatherSoloObj.icon+'.png'" alt="Weather icon" style="max-width: 45%;">
                <div class="side-card-body">
                    <h5 class="side-card-title">{{weatherSoloObj.temperature}}<span>°F</span></h5>
                    <h6 class="side-card-text">Feels like <b>{{weatherSoloObj.feelsLike}}<span>°F</span></b></h6>
                    <p class="side-card-text">{{weatherSoloObj.description.charAt(0).toUpperCase() + weatherSoloObj.description.slice(1) }}</p>
                </div>
            </div>
        </aside>
        <main class="main">
            <div class="main-header">
                <h3 class="todayHeader">Today's Highlights</h3>
                <div class="today-highlights">
                    <div class="today-group">
                        <div class="today-body">
                            <h6 class="card-title">Humidity</h6>
                            <p class="card-text"><i class="fas fa-cloud-sun-rain"></i> {{weatherSoloObj.humidity}} <span>%</span></p>
                        </div>
                        <div class="today-body">
                            <h6 class="card-title">Visibility</h6>
                            <p class="card-text"><i class="fas fa-smog"></i> {{(Math.round(weatherSoloObj.visibility * 100) / 100).toFixed(2)}} <span>miles</span></p>
                        </div>
                        <div class="today-body">
                            <h6 class="card-title">Wind Status</h6>
                            <p class="card-text"><i class="fas fa-wind"></i> {{weatherSoloObj.windSpeed }} <span>mph</span></p>
                        </div>
                        <div class="today-body">
                            <h6 class="card-title">Cloudiness</h6>                        
                            <p class="card-text"><i class="fas fa-cloud"></i> {{(weatherSoloObj.cloudiness) }} <span>%</span></p>
                        </div>
                        <div class="today-body">
                            <h6 class="card-title">Minimum temperature</h6>
                            <p class="card-text"><i class="fas fa-chevron-down"></i> {{(weatherSoloObj.minTemp) }} <span>°F</span></p>
                            <h6 class="card-title">Maximum temperature</h6>
                            <p class="card-text"><i class="fas fa-chevron-up"></i> {{(weatherSoloObj.maxTemp) }} <span>°F</span> </p>
                        </div>
                        <div class="today-body">
                            <h6 class="card-title">Wind Direction</h6>
                            <p class="card-text"><i class="fas fa-arrows-alt"></i> {{ weatherSoloObj.windDir }} <span>degrees</span></p>
                        </div>
                    </div>
                </div>
                <h3 class="todayHeader">This Week:</h3>
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
        </main>
    </fragment>
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
            // eslint-disable-next-line no-console
            console.log(this.weatherSoloObj.forecast)
            if (this.weatherSoloObj.forecast) {
                return this.weatherSoloObj.forecast.filter((value, index) =>  index % 8 == 0)
            }
            return []
        }
    }
}
</script>

<style scoped>
    div.card-group {
        font-size: 2em;
    }

    p.card-body {
        font-size: 2em;
    }

    p.card-text > span {
        font-size: 0.5em;
    }

    div.today-group {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-auto-rows: auto;
        grid-gap: 1rem;
        margin: 10px 0px;
        font-size: 2em;
    }

    div.today-body {
        border: 1px solid #aedada;
        border-radius: 20px;
        padding: 20px;
        background: white;
    }

    h3.todayHeader {
        font-size: 2em;
    }
</style>
