<template>
  <div class="container">
    <div class="row">
      <div class="col-12 col-md-6 col-xl-8 pr-4 order-2 order-md-1">
        <div class="row">
          <div class="col-12 h1 mt-4 mt-md-0">{{product.productName}}</div>
          <hr class="w-100 m-1">
          <div class="col-12 mt-3 mb-3">
            <div class="row align-items-center">
              <div class="col-auto h4">Колір:</div>
              <div class="col h5 font-weight-bold">{{product.color}}</div>
            </div>
          </div>
          <hr class="w-100 m-1">
          <div class="col-12 mt-3 mb-3">
            <div class="row align-items-center">
              <div class="col-auto h4">Розмір:</div>
              <div class="col">
                <div class="row no-gutters">
                  <div class="col-auto size-radio align-self-end" v-for="size in product.productSizes">
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
            <img :src="imageUrl" alt="">
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
      imageUrl() {
        const img = this.product.image.data;
        let arrayBuffer = img;
        let bytes = new Uint8Array(arrayBuffer);
      }
    },
    data() {
      return {
        product: {},
        selectedSize: ''
      }
    },
    methods: {
      ...mapActions({
        loadProduct: 'getProduct'
      })
    },
    created() {
      this.loadProduct(parseInt(this.$route.params.id))
              .then((data) => {
                this.product = data;
              })
              .catch(() => {
                this.$router.push('/404')
              });
    }
  }
</script>

<style scoped>
  @import "../assets/css/product-page.css";
</style>
