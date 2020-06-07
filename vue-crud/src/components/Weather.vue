<template>
<!-- <div class="container"> -->
<div class="row">
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <!-- <div class="sidebar-sticky pt-3">
        

        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
          <span>Saved reports</span>
          <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-plus-circle"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line></svg>
          </a>
        </h6>
        
      </div> -->
    <!-- <div class="container"> -->
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
  <!-- </div> -->
  <!-- <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4"> -->
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
  <!-- </main> -->
</div>
<!-- </div> -->
</template>

<script>
// import * as d3 from 'd3'

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
    mounted() {
        // let tempArr

        // this.weatherForecast = this.weatherSoloObj.forecastResponse
           // eslint-disable-next-line no-console
        console.log(this.weatherSoloObj)
        // const date = new Date('2010-08-05')
// const dateTimeFormat = new Intl.DateTimeFormat('en', { year: 'numeric', month: 'short', day: '2-digit' }) 
// const [{ value: month },,{ value: day },,{ value: year }] = dateTimeFormat .formatToParts(date ) 

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
    nav#sidebarMenu {
        /* height: calc(100vh - 54px); */
    }
    div.card {
        border: 1px solid lightgrey;
    }
</style>
