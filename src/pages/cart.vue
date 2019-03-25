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
            <cart-item class="col-12" v-for="cartItem in cartItems" :key="cartItem.id" :cart-item="cartItem"></cart-item>
        </div>
        <router-link to="/create-order" tag="button" class="row">Створити замовлення</router-link>
    </div>
</template>
<script>
    import {mapGetters, mapActions} from 'vuex'
    import CartItem from "../components/CartItem";

    export default {
        components: {
            'cart-item' : CartItem
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
