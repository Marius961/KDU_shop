<template>
    <main class="container">
      <div class="row">
        <h2>Популярні товари</h2>
      </div>
      <div class="row">
        <card
          v-for="item in items"
          :key="'product' + item.id"
          class="col-12 col-sm-6 col-md-4 col-lg-3"
          :item-id="item.id"
          :item-name="item.name"
          :item-img="item.image"
          :item-price="item.price"
        >
        </card>
      </div>
    </main>
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
  @import "../assets/css/home.css";
</style>
