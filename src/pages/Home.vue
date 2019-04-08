<template>
    <main class="container">
        <div class="row">
            <div class="col-12 p-0 order-1 order-md-1">
                <div id="carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="../assets/img/slider/slider-1.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="../assets/img/slider/slider-2.png" class="d-block w-100" alt="...">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
        <div class="row mt-2 mb-2">
            <hr class="w-100">
            <router-link
                    class="col-auto category-link"
                    v-for="category in categories"
                    :key="'cl' + category.id"
                    :to="{ path: '/products/' + category.categoryUrl, query: {pageNum: 0}}"
                    tag="div"
                >
                {{category.categoryName}}
            </router-link>
            <hr class="w-100">
        </div>
        <div class="row">
            <div class="col-12 popular-products-segment" :class="{'show-all': showAllPopularProducts}">
                <div class="row">
                    <h2>Популярні товари</h2>
                </div>
                <div class="row list">
                    <card
                            v-for="product in products"
                            :key="'popular' + product.id"
                            class="col-12 col-sm-6 col-md-4 col-lg-3"
                            :product="product"
                    >
                    </card>
                </div>
                <div class="row justify-content-center">
                    <button class="col-auto more-btn" @click="showAllPopularProducts = !showAllPopularProducts">
                        <i class="fas fa-chevron-circle-down"></i>
                        Показати більше
                    </button>
                </div>
            </div>
        </div>
    </main>
</template>

<script>

    import ItemCard from '../components/ItemCard'
    import {mapGetters, mapActions} from 'vuex'

    export default {
        data() {
            return {
                products: [],
                showAllPopularProducts: false
            }
        },
        computed: {
            ...mapGetters({
                categories: 'getCategories'
            })
        },
        components: {
            'card': ItemCard
        },
        methods: {
            ...mapActions({
                getPopularProducts: 'getPopularProducts'
            })
        },
        created() {
            this.getPopularProducts()
                .then(data => {
                    this.products = data;
                })
                .catch(() => {
                    this.$router.push('/errors/404')
                })
        }

    }
</script>

<style scoped>
    @import "../assets/css/home.css";
</style>
