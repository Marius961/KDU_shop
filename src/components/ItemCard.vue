<template>
    <div>
        <router-link :to="'/product/' + product.id" tag="div" class="row no-gutters justify-content-center h-100 card-link">
            <div class="col-auto p-0 card-body">
                <div class="row h-100 no-gutters">
                    <div class="col-12 p-0 align-self-center text-center">
                        <img :src="product.imageName ? staticImgPath + product.imageName : ''" alt="">
                    </div>
                    <div class="col-12 item-info">
                        <div class="row align-items-center justify-content-between pr-3 pl-3 pt-2 pb-1">
                            <div class="col item-title">{{product.name}}</div>
                            <div class="col-auto item-price">{{product.price}}&#8372;</div>
                        </div>
                        <div class="row pr-3 pl-3 pt-2 pb-1 params">
                            <p class="col-12" v-if="product.description">
                                {{product.description}}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </router-link>
    </div>
</template>

<script>

    export default {
        props: {
            product: Object
        },
        data() {
            return {
                staticImgPath: 'http://localhost:8090/img/'
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/itemCard.css";
</style>
