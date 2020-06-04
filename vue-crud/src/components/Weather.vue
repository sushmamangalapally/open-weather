<template>
<div class="container">
<div class="row">
  <div class="col-4">

    <!-- <div class="container"> -->
        <div class="card bg-light mb-3" style="max-width: 18rem;">
            <div class="card-header">{{weatherSoloObj.location}}</div>
            <img class="card-img" :src="'http://openweathermap.org/img/w/'+weatherSoloObj.icon+'.png'" alt="Weather icon" style="max-width: 45%;">
            <div class="card-body">
                <h5 class="card-title">{{weatherSoloObj.temperature}}</h5>
                <p class="card-text">{{weatherSoloObj.description}}</p>
            </div>
        </div>

  <!-- </div> -->
  </div>
  <div class="col-8">

<figure class="highcharts-figure">
  <div id="container"></div>
    </figure> 

  </div>
</div>
</div>
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
                }
            }
        },
        averages: {
            type: Array,
            default: function () {
                return []
            }
        }
    },
    data() {
        return {
            weatherForecast: []
        }
    },
    mounted() {
        // let tempArr

        // this.weatherForecast = this.weatherSoloObj.forecastResponse
           // eslint-disable-next-line no-console
        console.log(this.weatherSoloObj)
    }
}
</script>

<style scoped>
    .line {
      fill: none;
      stroke-width: 1.5px;
    }

</style>
