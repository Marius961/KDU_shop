<template>
  <div>
    <div class="row no-gutters justify-content-center h-100">
      <div class="col-auto p-0 card-body">
        <div class="row h-100 no-gutters">
          <div class="col-12 p-0 align-self-center text-center">
            <img :src="item.image" alt="">
          </div>
          <div class="col-12 item-info">
            <div class="row align-items-center justify-content-between pr-3 pl-3 pt-2 pb-1">
              <div class="col item-title">{{item.name}}</div>
              <div class="col-auto item-price">{{item.price}}&#8372;</div>
            </div>
            <div class="row pr-3 pl-3 pt-2 pb-1 params">
              <div v-if="item.colors.length > 0" class="col-12 color-select">Кольори</div>
              <div class="col-12">
                <div v-if="item.colors.length > 0" class="row no-gutters">
                  <label v-for="itemColor in item.colors" :key="'color' + itemColor.id" class="col-auto color-radio">
                    <input type="radio" v-model="color" :value="itemColor">
                    <span class="checkmark" :style="{background: itemColor.colorCode}"></span>
                  </label>
                </div>
                <div class="row no-gutters mb-1 mt-1">
                  <div class="col">
                    <input type="number" v-model="quantity" name="" id="">
                  </div>
                </div>
                <div class="row no-gutters justify-content-end pt-3 pb-3 pr-2">
                  <button class="col-auto to-cart-btn" @click="addItemToCart(item)">У кошик</button>
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

  import {mapMutations} from 'vuex'

  export default {
    props: {
      item: Object
    },
    data() {
      return {
        quantity: 1,
        color: this.item.colors[0]
      }
    },
    methods: {
      ...mapMutations({
        addToCart: 'addItemToCart'
      }),
      addItemToCart(item) {
        this.addToCart({
          id: item.id,
          name: item.name,
          image: item.image,
          color: this.color,
          quantity: this.quantity
        })
      }
    }
  }
</script>

<style scoped>
  @import "../assets/css/cardItem.css";
</style>
