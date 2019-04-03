<template>
    <div class="container">
        <div class="row admin-panel-header">
            <div class="col-12 h2">
                <i class="fas fa-user-shield"></i>
                Панель адміністратора
            </div>
        </div>
        <hr class="w-100">
        <div class="row mb-0 tab-panel">
            <router-link to="/admin-panel/categories" active-class="item-active" tag="div" class="col-auto item">
                Керування категоріями
            </router-link>
            <router-link to="/admin-panel/products" active-class="item-active" tag="div" class="col-auto item">
                Керування товарами
            </router-link>
            <router-link to="/orders-manage" active-class="item-active" tag="div" class="col-auto item">
                Керування Замовленнями
            </router-link>
        </div>
        <router-view class="panel"></router-view>
    </div>
</template>

<script>
    export default {
        name: "adminPanel"
    }
</script>

<style scoped>
    @import "../../assets/css/adminPanel.css";


</style>
