<template>
    <div class="container">
        <div class="card bg-light mb-3" style="max-width: 18rem;">
            <!-- <div v-if="this.displayLocation.length"> -->
                <div class="card-header">{{weatherSoloObj.location}}</div>
                <img class="card-img" :src="'http://openweathermap.org/img/w/'+weatherSoloObj.icon+'.png'" alt="Weather icon" style="max-width: 45%;">
                <div class="card-body">
                    <h5 class="card-title">{{weatherSoloObj.temperature}}</h5>
                    <p class="card-text">{{weatherSoloObj.description}}</p>
                </div>
            <!-- </div>
            <div v-else>
                <div class="card-header">Not a valid city!</div>
            </div> -->
        </div>
    </div>
</template>

<script>
export default {
    name: "Weather",   
    props: {
    weatherSoloObj: {
        type: Object,
        default: function () { return {

                location: "",
                description: "",
                temperature: 0.0,
                iconLink: "",
        } }
    }
    },
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
            // weatherSoloObj: {
            //     location: "",
            //     icon: "",
            //     temperature: 0,
            //     description: ""
            // }
        };
    },
    mounted() {
        // eslint-disable-next-line no-console
        console.log(this);
        // this.weatherSoloObj.location = this.$attrs.location;
        // this.weatherSoloObj.icon = this.$attrs.location;
        // this.weatherSoloObj.temperature = this.$attrs.temperature;
        // this.weatherSoloObj.description = this.$attrs.description;
    }
}
</script>

<style scoped>

</style>
