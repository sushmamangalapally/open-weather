<template>
    <div class="container">
        <div class="card bg-light mb-3" style="max-width: 18rem;">
            <div class="card-header">{{weatherSoloObj.location}}</div>
            <img class="card-img" :src="'http://openweathermap.org/img/w/'+weatherSoloObj.icon+'.png'" alt="Weather icon" style="max-width: 45%;">
            <div class="card-body">
                <h5 class="card-title">{{weatherSoloObj.temperature}}</h5>
                <p class="card-text">{{weatherSoloObj.description}}</p>
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
                }
            }
        }
    }
}
</script>

<style scoped>

</style>
