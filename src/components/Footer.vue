<template>
    <footer class="container-fluid">
        <div class="row justify-content-center footer">
            <div class="col-7 col-md-5 col-lg-4 col-xl-3 info-segment">
                <div class="row contact-info">
                    <h4 class="col-12">Контактна інформація</h4>
                    <div class="col-12 info-field">
                        <i class="fas fa-university"></i>
                        Університет Короля Данила
                    </div>
                    <div class="col-12 info-field">
                        <i class="fas fa-map-marker-alt"></i>
                        м.Івано-Франківськ, вул. Є.Коновальця, 35
                    </div>
                    <div class="col-12 info-field">
                        <i class="fas fa-phone"></i>
                        +38 (0342) 77 18 45
                    </div>
                    <div class="col-12 info-field">
                        <i class="far fa-envelope"></i>
                        university@iful.edu.ua
                    </div>
                    <a href="//iful.edu.ua" class="col-12 info-field">
                        <i class="fas fa-globe-asia"></i>
                        iful.edu.ua
                    </a>
                </div>
            </div>
            <div class="col-4 col-md-3 info-segment">
                <div class="row">
                    <h4 class="col-12">Посилання</h4>
                    <router-link to="/" class="col-12">Головна</router-link>
                </div>
            </div>
            <div class="col-12 col-md-3 col-lg-2 info-segment">
                <div class="row">
                    <h4 class="col-12 text-center text-md-left">Соц. мережі</h4>
                    <div class="col-auto social-link">
                        <i class="fab fa-facebook-square"></i>
                    </div>
                    <div class="col-auto social-link">
                        <i class="fab fa-youtube"></i>
                    </div>
                    <div class="col-auto social-link">
                        <i class="fab fa-instagram"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="row copyright-segment justify-content-center">
            <div class="col-auto">
                © 2019 KDU Shop
            </div>
        </div>
    </footer>
</template>

<script>
    export default {

    }
</script>

<style scoped>
    @import "../assets/css/footer.css";
</style>
