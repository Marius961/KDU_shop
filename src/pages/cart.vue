<template>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="row justify-content-center">
                    <div class="col-auto">Кінцева ціна <span class="font-weight-bold">{{totalCartPrice}}</span></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div v-if="cartItems.length <= 0" class="col-12 h1">Корзина пуста</div>
            <div class="col-12 item" v-for="cartItem in cartItems">
                <div class="row">
                    <div class="col-3">
                        <div class="row justify-content-center">
                            <div class="col-auto">
                                <img :src="staticImgPath + cartItem.product.imageName" alt="">
                            </div>
                            <div class="w-100"></div>
                            <div class="col- mt-2">
                                <div class="row quantity-bar">
                                    <div @click="changeQuantity(cartItem.id, cartItem.quantity - 1)" class="col-4 text-center"><i class="fas fa-minus"></i></div>
                                    <div class="col-4 text-center font-weight-bold text-center">{{cartItem.quantity}}</div>
                                    <div @click="changeQuantity(cartItem.id, cartItem.quantity + 1)" class="col-4 text-center"><i class="fas fa-plus"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-9">
                        <div class="row justify-content-between align-items-start">
                            <div class="col-auto item-header">{{cartItem.product.name}}</div>
                            <div @click="deleteFromCart(cartItem.id)" class="col-auto delete-btn"><i class="fas fa-times"></i></div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="row">
                                    <div class="col-auto">Колір</div>
                                    <div class="col-auto font-weight-bold">{{cartItem.product.color}}</div>
                                </div>
                            </div>
                            <div class="col-12" v-if="cartItem.product.sizes">
                                <div class="row">
                                    <div class="col-auto">Розмір</div>
                                    <div class="col-auto font-weight-bold">{{cartItem.size}}</div>
                                </div>
                            </div>
                            <div class="col-12" v-if="cartItem.product.sizes">
                                <div class="row">
                                    <div class="col-auto">Ціна</div>
                                    <div class="col-auto font-weight-bold">{{cartItem.product.price * cartItem.quantity}}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-end">
                    <router-link :to="'/product/' + cartItem.product.id" href="#" class="col-auto text-right align-self-end details-link"><i class="fas fa-info-circle"></i> Детальніше</router-link>
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
                cartItems: 'getCartItemsList',
                totalCartPrice: 'totalCartPrice'
            }),
        },
        methods: {
            ...mapActions({
                getCart: 'getCartItems',
                deleteCartItem: 'deleteCartItem',
                changeItemQuantity: 'changeItemQuantity'
            }),
            deleteFromCart(itemId) {
                this.deleteCartItem(itemId)
                    .catch(() => {
                        alert("Сталась помилка підчас видалення")
                    })
            },
            changeQuantity(itemId, quantity) {
                if (quantity > 0) {
                    this.changeItemQuantity({itemId, quantity})
                        .catch(() => {
                            alert("Виникла помилка підчас зміни кількості")
                        })
                }
            }
        },
        created() {
            this.getCart();
        }
    }
</script>

<style scoped>
    @import "../assets/css/cart.css";
</style>
