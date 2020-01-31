<template>
  <div>
    <WeatherApp />
  </div>
</template>
<script>
import WeatherApp from './components/WeatherApp.vue'

export default {
  name: 'app',
  components: {
    WeatherApp
  }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css);
* {
  margin: 0;
  padding: 0
}
li.nav-item {
    /* margin: 0px 10px 0px 10px;
    padding: 0 2px 0 2px;
    border: 1px solid #381077; */
    margin: 0px 3px 0px 5px;
    padding: 0 25px 0 3px;
    /* border: 1px solid #381077; */
    /* border-radius: 5px; */
    /* background: #635273; */
    color: white;
    border-right: 2px solid rebeccapurple;
}
.city-info {
  cursor: pointer;
}
.navbar {
  background: #635273;
}

#navbarSupportedContent {
  background: #635273;
    padding: 0px 10px 0px 10px;
}

ul.navbar {
  background: #635273;
}

.container-fluid {
  padding-left: 0px;
  padding-right: 0px;
}

div.card {
  margin-top: 5%;
}
</style>