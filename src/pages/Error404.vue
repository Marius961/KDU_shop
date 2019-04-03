<template>
    <div class="container">
        <div class="row">
            <div class="col-12 error-code">
                <span>404</span>
                <h4>Схоже що сторінки, яку Ви шукаєте, не існує</h4>
            </div>
        </div>
        <div class="row justify-content-center">
            <router-link to="/" tag="button" class="col-auto submit-btn">Перейти на головну сторінку</router-link>
        </div>
    </div>
</template>

<script>
    export default {
    }
</script>

<style scoped>
    @import "../assets/css/Error404.css";
</style>
