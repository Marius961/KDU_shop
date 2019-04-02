<template>
    <div class="row">
        <div class="col-12 col-md-3 col-xl-2">
            <div class="row">
                <router-link to="/admin-panel/products/add" active-class="item-active" tag="div" class="col-auto col-md-12 item">
                    Дадати товар
                </router-link>
                <router-link to="/admin-panel/products/list" active-class="item-active" tag="div" class="col-auto col-md-12 item">
                    Список товарів
                </router-link>
            </div>
        </div>
        <div class="col-12 col-md-9 col-xl-10">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
    export default {

    }
</script>

<style scoped>
    @import "../../../assets/css/adminPanel.css";

</style>
