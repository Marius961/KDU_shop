<template>
  <nav class="nav-bar">
    <div class="container">
      <div class="row justify-content-between">
        <div class="col-auto logo">
          <img src="../assets/img/KDU_logo.png" alt="">
        </div>
        <div class="col-12 col-md menu">
          <div class="row">
            <div class="col-12 col-md-auto p-0 pl-md-3 pr-md-3">
              <div class="row">
                <div class="col-auto col-md-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/home.png" alt="">
                    <span>ГОЛОВНА</span>
                  </div>
                </div>
                <div class="col-12 col-md-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/t-shirt.png" alt="">
                    <span>ПРОДУКЦІЯ</span>
                  </div>
                  <div class="item-dropdown">
                    <a href="#">Футболки</a>
                    <a href="#">Толстовки</a>
                    <a href="#">Аксесуари</a>
                    <a href="#">Різне</a>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-12 col-md">
              <div class="row justify-content-end">
                <div class="col-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/user.png" alt="">
                  </div>
                </div>
                <div class="col-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/cart.png" alt="">
                  </div>
                </div>
                <div class="col col-md-4 search-field">
                  <div class="link-content">
                    <img src="../assets/img/search.png" alt="">
                    <input type="search" name="" id="">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
    export default {

    }
</script>

<style scoped>
  @import "../assets/css/nav.css";
</style>
