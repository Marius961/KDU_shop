<template>
  <div class="container">
    <div class="row">
      <div class="col-6 item" v-for="item in cartItems">
        <div class="row">
          <div class="col-4">
            <img :src="item.image" alt="">
          </div>
          <div class="col-8">
            <div class="row">
              <div class="col-12 h4">{{item.name}}</div>
              <div class="col-6">
                <div class="row">
                  <div class="col-12 text-center">Кількість</div>
                  <div class="col-12 font-weight-bold text-center">{{item.quantity}}</div>
                </div>
              </div>
              <div class="col-6">
                <div class="row">
                  <div class="col-12 text-center">Колір</div>
                  <div class="col-12 font-weight-bold text-center">{{item.color.colorName}}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'

  export default {
    computed: {
      ...mapGetters({
        cartItems: 'getCartItems'
      })
    }
  }
</script>

<style scoped>
  @import "../assets/css/cart.css";
</style>
