import Vue from "vue";
import Router from "vue-router";
import WeatherApp from '@/components/WeatherApp';

Vue.use(Router);

const router = new Router({
  mode: "history", // Use browser history
  routes: [
    {
      path: "/",
      name: "Home",
      component: WeatherApp
    },
    {
      path: "/:location",
      name: "Weather",
      component: WeatherApp
    }
  ]
});

export default router;
