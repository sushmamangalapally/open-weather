<template>
  <WeatherApp />
</template>

<script>
import Vue from 'vue'
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

import WeatherApp from './components/WeatherApp.vue'
import Fragment from 'vue-fragment'
Vue.use(Fragment.Plugin);

export default {
    name: 'app',
    components: {
        WeatherApp
    }
}
</script>

<style>
  * {
    margin: 0;
    padding: 0
  }
  .grid-container {
    display: grid;
    grid-template-columns: 340px 1fr;
    grid-template-rows: 80px 1fr 50px;
    grid-template-areas: 
        "header header"
        "sidenav main"
        "sidenav footer"
    ;
    height: 100vh;
    font-family: 'Montserrat', sans-serif;
  }

  .header {
    grid-area: header;
    background: lightskyblue;
  }

  .sidenav {
    grid-area: sidenav;
    color: white;
  }

  .main {
    grid-area: main;
    background: #f9ffff;
  }

  .footer {
    grid-area: footer;
    background: lightgrey;
  }

  .header, .footer {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 20px;
    background: linear-gradient(90deg, rgba(69,105,85,0.5331412103746398) 8%, rgba(0,102,102,1) 65%);
  }

  div.nav-item {
    margin: 0px 3px 0px 5px;
    padding: 0 25px 0 3px;
    color: white;
    border-right: 2px solid rebeccapurple;
    list-style: none;
    display: inline;
    display: none;
    height: 80px;
    border-right: 1px solid;
    padding: 10px;
    background: none;
  }

  .main-header {
    display: flex;
    justify-content: space-between;
    margin: 20px;
    padding: 20px;
    color: slategray;
    flex-direction: column;
  }

  .card-group {
    display: grid;
    grid-template-columns: repeat(5, 1fr); /* Where the magic happens */
    grid-auto-rows: auto;
    grid-gap: 1rem;
    margin: 10px 0px;
  }

  .card {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
    /* background-color: #d3d3; */
    border: 1px solid #aedada;
        border-radius: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    background: white;
  }

  .grid-container {
    display: grid;
    grid-template-columns: 1fr; /* Side nav is hidden on mobile */
    grid-template-rows: 50px 100px 1fr 50px;
    grid-template-areas:
      'header'
      'sidenav'
      'main'
      'footer';
    height: 100vh;
  }
  
  .sidenav {
    display: flex;
    grid-area: sidenav;
    background-color: #006666;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  div.nav-item {
      display: none;
  }

  div.card-body {
      text-align: center;
  }

  div.card-body  > img {
      width: 60%;
  }

  .side-card-text {
      font-size: 25px;
    letter-spacing: 1px;
    margin-bottom: 10px;
    text-align: center;
  }

  .side-card-header {
    font-size: 4em;
    font-weight: 600;
    text-align: center;  }

  .side-card-img {
    width: 150px;
    display: flex;
    margin: auto;
  }

  .side-card-title {
    font-size: 2.5em;
    text-align: center;
    margin-bottom: 50px;  }

  .side-card-text span {
    font-size: .5em;
    position: relative;
    bottom: 1em;
  }

  div.navbar {
    margin-bottom: 30px;
  }

  input {
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
    font-size: 25px;
    border: none;
    border-radius: 10px;
  }

  form > button {
    padding: 12px 20px;
    margin: 8px 8px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    font-size: 25px;
    border: none;
    border-radius: 10px;
    cursor: pointer;
  }
  
  /* Non-mobile styles, 750px breakpoint */
  @media only screen and (min-width: 46.875em) {
    /* Show the sidenav */
    .grid-container {
        display: grid;
        grid-template-columns: 340px 1fr;
        grid-template-rows: 80px 1fr 50px;
        grid-template-areas: 
            "header header"
            "sidenav main"
            "sidenav footer"
        ;
        height: 100vh;
    }

    .sidenav {
      display: flex;
      flex-direction: column;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    div.nav-item {
        display: inline;
        cursor: pointer;
    }
  }

  /* Medium-sized screen breakpoint (tablet, 1050px) */
  @media only screen and (min-width: 65.625em) {

  }
</style>
