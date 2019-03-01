<template>
  <div class="container">
    <div class="row">
      <div class="col-12 col-md-6 col-xl-8 pr-4 order-2 order-md-1">
        <div class="row">
          <div class="col-12 h1 mt-4 mt-md-0">{{product.name}}</div>
          <hr class="w-100 m-1">
          <div class="col-12 mt-3 mb-3">
            <div class="row align-items-center">
              <div class="col-auto h4">Колір:</div>
              <div class="col h5 font-weight-bold">чорний</div>
            </div>
          </div>
          <hr class="w-100 m-1">
          <div class="col-12 mt-3 mb-3">
            <div class="row align-items-center">
              <div class="col-auto h4">Розмір:</div>
              <div class="col">
                <div class="row no-gutters">
                  <div class="col-auto size-radio align-self-end" v-for="size in product.sizes">
                    <input type="radio" :id="'size' + size.id" v-model="selectedSize" :value="size.id">
                    <label class="wrapper" :for="'size' + size.id">{{size.name}}</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <hr class="w-100 m-1">
          <div class="col-12 mt-3 mb-3">
            <div class="row align-items-center">
              <div class="col-12 h4">Опис</div>
              <p class="col-12">{{product.description}}</p>
            </div>
          </div>
          <div class="col-12 mb-5">
            <div class="row pl-3">
              <button class="col-12 p-3 p-md-2 pl-md-4 pr-md-4 col-md-auto to-cart-btn cart-btn-text">У кошик</button>
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 col-md-6 order-1 order-md-2 col-xl-4 mt-5">
        <div class="row">
          <div class="col-12 img-segment">
            <img :src="product.image" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

  import  {mapActions} from 'vuex'
  export default {
    computed: {
    },
    data() {
      return {
        quantity: 1,
        selectedSize: 0,
        product: {}
      }
    },
    methods: {
      ...mapActions({
        loadProduct: 'getProduct'
      })
    },
    created() {
      const productFromDb = this.getProduct(parseInt(this.$route.params.id));
      if (typeof productFromDb === 'undefined') {
        this.$router.push('/404')
      } else {
        this.product = productFromDb;
      }
    }
  }
</script>

<style scoped>
  @import "../assets/css/product-page.css";
</style>
