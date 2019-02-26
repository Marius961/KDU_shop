<template>
    <div class="container">
      <div class="row">
        <div class="col-3 filters">
          <div class="row filters-group">
            <div class="col-12 group-name">Розмір</div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">XS
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">S
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">L
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">XL
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">XXL
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
          <div class="row filters-group">
            <div class="col-12 group-name">Колір</div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Чорний
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Білий
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Сірий
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Червоний
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Фіолетовив
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
        </div>
        <div class="col-9">
          <div class="row">
            <card
              v-for="item in items"
              :key="'product' + item.id"
              class="col-4"
              :item-id="item.id"
              :item-name="item.name"
              :item-img="item.image"
              :item-price="item.price"
            >
            </card>
          </div>
        </div>
      </div>
    </div>
</template>

<script>

  import ItemCard from '../components/ItemCard'
  import {mapGetters} from 'vuex'

  export default {

    computed: {
      ...mapGetters({
        items: 'getProducts'
      })
    },
    components: {
      'card': ItemCard
    }
  }
</script>

<style scoped>
  @import "../assets/css/products.css";
</style>
