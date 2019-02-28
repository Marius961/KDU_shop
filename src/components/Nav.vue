<template>
  <nav class="nav-bar mb-3">
    <div class="container">
      <div class="row justify-content-between">
        <div class="col-auto logo">
          <img src="../assets/img/nav-icons/KDU_logo.png" alt="">
        </div>
        <div
          class="col-auto nav-link d-md-none"
          @click="isShowNavMenu = !isShowNavMenu"
        >
          <div class="link-content">
            <img src="../assets/img/nav-icons/menu.png" alt="">
          </div>
        </div>
        <div
          class="col-12 col-md d-md-block menu"
          :class="{'show-menu': isShowNavMenu}"
        >
          <div class="row">
            <div class="col-12 col-md-auto p-0 pl-md-3 pr-md-3">
              <div class="row no-gutters">
                <router-link tag="div" to="/" class="col-12 col-auto col-md-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/nav-icons/home.png" alt="">
                    <span>ГОЛОВНА</span>
                  </div>
                </router-link>
                <div class="col-12 col-md-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/nav-icons/t-shirt.png" alt="">
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
                <div v-if="isAuthenticated" class="col-12 col-md-4 col-lg-3 col-xl-2 nav-link">
                  <div class="link-content">
                    <img src="../assets/img/nav-icons/user.png" alt="">
                    <span>{{username}}</span>
                  </div>
                  <div class="item-dropdown">
                    <a href="#">Замовлення</a>
                    <router-link to="/add-category" href="#" v-if="isAdmin">Додати категорію</router-link>
                    <a href="#" v-if="isAdmin">Додати товар</a>
                    <a href="#" @click.prevent="logoutUser">Вийти</a>
                  </div>
                </div>
                <div v-if="!isAuthenticated" class="col-12 col-auto col-md-auto nav-link">
                  <div class="link-content">
                    <span>
                      <router-link to="/registration">Реєстрація</router-link> |
                      <router-link to="/login">Вхід</router-link>
                    </span>
                  </div>
                </div>
                <router-link  tag="div" to="/cart" class="col-auto nav-link">
                  <div class="link-content">
                    <img src="../assets/img/nav-icons/cart.png" alt="">
                  </div>
                </router-link>
                <div class="col col-md-4 search-field">
                  <div class="link-content">
                    <img src="../assets/img/nav-icons/search.png" alt="">
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

  import {mapGetters} from 'vuex'
  import {mapMutations} from 'vuex'
  import rolesHelper from '../helpers/rolesHelper'

  export default {
    computed: {
      ...mapGetters({
        isAuthenticated: 'isAuthenticated',
        username: 'getUsername'
      }),
      isAdmin() {
        return rolesHelper.isUserHasRole('ADMIN')
      }
    },
    data() {
      return {
        isShowNavMenu: false
      }
    },
    methods: {
      ...mapMutations({
        logout: 'LOGOUT'
      }),
      logoutUser() {
        this.logout();
        this.$router.push('/');
      }
    }
  }
</script>

<style scoped>
  @import "../assets/css/nav.css";
</style>
