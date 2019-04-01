<template>
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-6 col-xl-6 pr-4 order-2 order-md-1">
                <div class="row">
                    <div class="col-12 h1 mt-4 mt-md-0">{{product.name}}</div>
                    <hr class="w-100 m-1">
                    <div class="col-12 mt-3 mb-3" v-if="product.color">
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
                                    <div class="col-auto size-radio align-self-end" v-for="size in productSizeList">
                                        <input type="radio" :id="'size' + size" v-model="selectedSize" :value="size">
                                        <label class="wrapper" :for="'size' + size">{{size}}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 mt-3 mb-3">
                        <div class="row form-group-1">
                            <label for="quantity">Кількість
                                <input v-model="quantity" id="quantity" type="number">
                            </label>
                        </div>
                    </div>
                    <hr class="w-100 m-1">
                    <div class="col-12 mt-3 mb-3" v-if="product.description">
                        <div class="row align-items-center">
                            <div class="col-12 h4">Опис</div>
                            <p class="col-12">{{product.description}}</p>
                        </div>
                    </div>
                    <div class="col-12 mb-5">
                        <div class="row pl-3">
                            <button @click="addItemToCart" class="col-12 col-md-auto submit-btn">У кошик</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 order-1 order-md-2 col-xl-6 mt-5">
                <div class="row justify-content-center">
                    <div class="col-8 col-sm-auto p-0 text-center img-segment">
                        <img :src="productImageUrl"  alt="">
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
            productImageUrl() {
                return this.product.imageName ? this.staticImgPath + this.product.imageName : ''
            },
            productSizeList() {
                return this.product.sizes ? this.product.sizes.split(',') : [];
            }
        },
        data() {
            return {
                product: {},
                selectedSize: "",
                quantity: 1,
                staticImgPath: 'http://localhost:8090/img/'
            }
        },
        methods: {
            ...mapActions({
                loadProduct: 'getProduct',
                addToCart: 'postCartItem'
            }),
            setDefaultProductSize() {
                const list = this.productSizeList;
                this.selectedSize = list[0];
            },
            addItemToCart() {
                let cartItem = {
                    size: this.selectedSize,
                    product: {
                        id: this.product.id
                    },
                    quantity: this.quantity > 0 ? this.quantity : 1,
                    totalPrice: this.product.price * this.quantity
                };

                this.addToCart(cartItem)
                    .then(() => {
                        this.$swal.fire({
                            type: 'success',
                            title: 'Товар додано у кошик',
                            showConfirmButton: false,
                            timer: 1000
                        })
                    })
                    .catch(() => {
                        this.$swal.fire({
                            type: 'error',
                            title: 'Невдалось додати товар у кошик',
                            showConfirmButton: false,
                            timer: 2000
                        })
                    })
            }
        },
        created() {
            this.loadProduct(this.$route.params.id)
                .then((data) => {
                    this.product = data;
                    this.setDefaultProductSize();
                })
                .catch(() => {
                    this.$router.push('/errors/404')
                });
        }
    }
</script>

<style scoped>
    @import "../assets/css/product-page.css";
    @import "../assets/css/form.css";
</style>
