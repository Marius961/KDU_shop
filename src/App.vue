<template>
  <div>
    <nav-bar></nav-bar>
    <router-view></router-view>
  </div>
</template>

<script>

  import Nav from './components/Nav'

  export default {
    components: {
      'nav-bar': Nav,
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
