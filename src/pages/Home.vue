<template>
    <main class="container">
      <div class="row">
        <h2>Популярні товари</h2>
      </div>
      <div class="row">
        <card
          v-for="product in products"
          :key="'product' + product.id"
          class="col-12 col-sm-6 col-md-4 col-lg-3"
          :id="product.id"
          :name="product.name"
          :image-name="product.imageName"
          :price="product.price"
        >
        </card>
      </div>
    </main>
</template>

<script>

    import ItemCard from '../components/ItemCard'
    import {mapGetters, mapActions} from 'vuex'

    export default {
        data() {
            return {
                products: []
            }
        },
        computed: {
            ...mapGetters({
                items: 'getProducts'
            })
        },
        components: {
            'card': ItemCard
        },
        methods: {
            ...mapActions({
                loadAllProducts: 'getAllProducts'
            })
        },
        created() {
            this.loadAllProducts()
                .then(data => {
                    this.products = data;
                    console.log(data)
                })
                .catch(() => {
                    this.$router.push('/404')
                })
        }

    }
</script>

<style scoped>
  @import "../assets/css/home.css";
</style>
