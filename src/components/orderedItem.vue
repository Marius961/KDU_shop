<template>
    <div class="row ordered-item">
        <div class="col-auto col-sm-6 col-md-8 product-name">{{orderedItem.product.name}}</div>
        <div class="col-auto col-sm-3 col-md-2 product-info">{{orderedItem.quantity}} шт</div>
        <div class="col-auto col-sm-3 col-md-2 product-info">{{orderedItem.quantity * orderedItem.product.price}} грн</div>
    </div>
</template>

<script>
    export default {
        props: {
            orderedItem: Object
        }
    }
</script>

<style scoped>
    @import "../assets/css/orderedItem.css";
</style>
