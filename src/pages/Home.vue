<template>
    <main class="container">
        <div class="row mt-2 mb-2">
            <router-link
                    class="col-auto category-link"
                    v-for="category in categories"
                    :key="'cl' + category.id"
                    :to="{ path: '/products/' + category.categoryUrl, query: {pageNum: 0}}"
                    tag="div"
                >
                {{category.categoryName}}
            </router-link>
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
