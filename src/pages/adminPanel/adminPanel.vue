<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">
                <i class="fas fa-user-shield"></i>
                Панель адміністратора
            </div>
        </div>
        <hr class="w-100">
        <div class="row">
            <router-link to="/admin-panel/categories" active-class="item-active" tag="div" class="col-auto item">
                Категорії
            </router-link>
        </div>
        <hr class="w-100">
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: "adminPanel"
    }
</script>

<style scoped>
    @import "../../assets/css/adminPanel.css";

    .container {
        margin-top: 40px;
        margin-bottom: 40px;
    }
</style>
