import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  mode: "history", // Use browser history
  routes: [
    {
      path: "/",
      name: "Home",
      component: () => import("./components/WeatherApp")
    },
    {
      path: "/:location",
      name: "Weather",
      component: () => import("./components/WeatherApp")
    }
  ]
});

export default router;
