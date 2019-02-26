<template>
  <div>
    <div class="row no-gutters justify-content-center h-100">
      <div class="col-auto p-0 card-body">
        <div class="row h-100 no-gutters">
          <div class="col-12 p-0 align-self-center text-center">
            <img :src="itemImg" alt="">
          </div>
          <div class="col-12 item-info">
            <div class="row align-items-center justify-content-between pr-3 pl-3 pt-2 pb-1">
              <div class="col item-title">{{itemName}}</div>
              <div class="col-auto item-price">{{itemPrice}}&#8372;</div>
            </div>
            <div class="row pr-3 pl-3 pt-2 pb-1 params">
              <div class="col-12">
                <div class="row justify-content-end pt-3 pb-3 pr-2">
                  <router-link tag="button" :to="'/product/' + itemId" class="col-auto to-cart-btn">детальніше</router-link>
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

  export default {
    props: {
      itemName: String,
      itemPrice: Number,
      itemId: Number,
      itemImg: String
    }
  }
</script>

<style scoped>
  @import "../assets/css/cardItem.css";
</style>
