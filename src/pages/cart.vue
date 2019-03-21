<template>
    <div class="container">
        <div class="row">
            <div v-if="cartItems.length <= 0" class="col-12 h1">Корзина пуста</div>
            <div class="col-6 item" v-for="cartItem in cartItems">
                <div class="row">
                    <div class="col-4">
                        <img :src="staticImgPath + cartItem.product.imageName" alt="">
                    </div>
                    <div class="col-8">
                        <div class="row">
                            <div class="col-12 h4">{{cartItem.product.name}}</div>
                            <div class="col-6">
                                <div class="row">
                                    <div class="col-12 text-center">Кількість</div>
                                    <div class="col-12 font-weight-bold text-center">{{cartItem.quantity}}</div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="row">
                                    <div class="col-12 text-center">Колір</div>
                                    <div class="col-12 font-weight-bold text-center">{{cartItem.product.color}}</div>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="row">
                                    <div class="col-12 text-center">Розмір</div>
                                    <div class="col-12 font-weight-bold text-center">{{cartItem.size}}</div>
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
    import {mapGetters, mapActions} from 'vuex'

    export default {
        data() {
            return {
                staticImgPath: 'http://localhost:8090/img/'
            }
        },
        computed: {
            ...mapGetters({
                cartItems: 'getCartItemsList'
            })
        },
        methods: {
            ...mapActions({
                getCart: 'getCartItems'
            })
        },
        created() {
            this.getCart();
        }
    }
</script>

<style scoped>
    @import "../assets/css/cart.css";
</style>
