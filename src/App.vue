<template>
  <div>
    <nav-bar></nav-bar>
    <div class="w-100" style="margin-top: 30px"></div>
    <router-view :key="$route.fullPath"></router-view>
    <page-footer></page-footer>
  </div>
</template>

<script>

  import Nav from './components/Nav'
  import footer from "./components/Footer";

  export default {
    components: {
      'nav-bar': Nav,
      'page-footer': footer
    }
  }
</script>
<style>
  @import url('https://fonts.googleapis.com/css?family=Roboto+Condensed&subset=cyrillic');
  @import "assets/css/main.css";

  body {
    font-family: 'Roboto Condensed', sans-serif;
  }
</style>
