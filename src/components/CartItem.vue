<template>
    <div class="item">
        <div class="row">
            <div class="col-12 col-sm-4 col-md-3">
                <div class="row justify-content-center">
                    <div class="col-auto">
                        <img :src="staticImgPath + cartItem.product.imageName" alt="">
                    </div>
                    <div class="w-100"></div>
                    <div class="col-12 col-sm-8 mt-2">
                        <div class="row quantity-bar">
                            <div @click="changeQuantity(cartItem.id, cartItem.quantity - 1)" class="col-4 text-center"><i class="fas fa-minus"></i></div>
                            <div class="col-4 text-center font-weight-bold text-center">{{cartItem.quantity}}</div>
                            <div @click="changeQuantity(cartItem.id, cartItem.quantity + 1)" class="col-4 text-center"><i class="fas fa-plus"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-sm-8 col-md-9">
                <div class="row justify-content-between align-items-start">
                    <div class="col item-header">{{cartItem.product.name}}</div>
                    <div @click="deleteItem()" class="col-auto delete-btn"><i class="fas fa-times"></i></div>
                </div>
                <div class="row">
                    <div class="col-12 col-sm-auto item-info" v-if="cartItem.product.color">
                        <div class="row no-gutters justify-content-start justify-content-sm-center">
                            <i class="col-auto fas fa-palette"></i>
                            <span class="col-auto font-weight-bold">{{cartItem.product.color}}</span>
                        </div>
                    </div>
                    <div class="col-12 col-sm-auto item-info" v-if="cartItem.product.sizes">
                        <div class="row no-gutters justify-content-start justify-content-sm-center">
                            <i class="col-auto fas fa-tshirt"></i>
                            <span class="col-auto font-weight-bold">{{cartItem.size}}</span>
                        </div>
                    </div>
                    <div class="col-12 col-sm-auto item-info">
                        <div class="row no-gutters justify-content-start justify-content-sm-center">
                            <i class="col-auto fas fa-tags"></i>
                            <span class="col-auto font-weight-bold">{{cartItem.product.price * cartItem.quantity}} грн</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <router-link :to="'/product/' + cartItem.product.id" href="#" class="col-auto text-right align-self-end details-link"><i class="fas fa-info-circle"></i> Детальніше</router-link>
        </div>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                staticImgPath: 'http://localhost:8090/img/'
            }
        },
        props: {
            cartItem: Object
        },
        methods: {
            deleteItem() {
                this.$emit('deleteItem', this.cartItem.id)
            },
            changeQuantity(id, quantity) {
                this.$emit('changeQuantity', id, quantity)
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/cartItem.css";
</style>
