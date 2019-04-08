<template>
    <div class="container">
        <div class="row align-items-start">
            <div class="col-12">
                <div class="row justify-content-end filters-panel">
                    <button @click="isHideFilters = !isHideFilters" class="col-12 col-sm-auto">
                        <i class="fas fa-filter"></i>
                        {{isHideFilters ? 'Показати' : 'Приховати'}}
                        фільтри
                    </button>
                </div>
            </div>
            <div class="col-12 col-lg-3 filters" :class="{'hide-filters': isHideFilters}">
                <div class="row no-gutters filters-group">
                    <div class="col-12 group-name">Ціна</div>
                    <div class="col-5">
                        <div class="row no-gutters pr-1">
                            <label class="col-12">Від</label>
                            <input v-model="query.minPrice" class="col-12" type="number">
                        </div>
                    </div>
                    <div class="col-5">
                        <div class="row no-gutters">
                            <label class="col-12">до</label>
                            <input v-model="query.maxPrice" class="col-12" type="number">
                        </div>
                    </div>
                    <button class="col-2 align-self-end" @click="applyFilters">OK</button>
                </div>
                <div class="row filters-group" v-if="colorsList.length > 0">
                    <div class="col-12 group-name">Колір</div>
                    <div class="col-12 group-items" v-for="color in colorsList" :key="'color' + color">
                        <label class="checkbox-container">
                            <input @change="applyFilters" type="checkbox" v-model="query.colors" :value="color">{{color}}
                            <span class="checkmark"></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-12 col-lg-9">
                <div class="row">
                    <card
                            v-for="item in pageData.content"
                            :key="'product' + item.id"
                            class="col-12 col-sm-6 col-md-6 col-lg-4"
                            :product="item"
                    >
                    </card>
                </div>
                <div class="row justify-content-center pagination" v-if="pages.length > 1">
                    <router-link
                            :class="{'page-active': page.isCurrent}"
                            v-for="page in pages"
                            :key="'page' + page.name"
                            :to="{ path: page.url, query: page.query }"
                            class="col-auto"
                    >
                        {{page.name}}
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import ItemCard from '../components/ItemCard'
    import {mapActions} from 'vuex'

    export default {
        props: ["categoryUrl"],
        data() {
            return {
                isHideFilters: true,
                colorsList: [],
                pageData: {},
                pages: [],
                query: {
                    pageNum: this.$route.query.pageNum ? this.$route.query.pageNum : 0,
                    minPrice: this.$route.query.minPrice ? this.$route.query.minPrice : undefined,
                    maxPrice: this.$route.query.maxPrice ? this.$route.query.maxPrice : undefined,
                    colors: this.$route.query.colors ? this.$route.query.colors.split(',') : []
                },
                pageSize: 16,
            }
        },
        computed: {
            getQueryParams() {
                let result = {};
                result.pageNum = this.query.pageNum;
                if (this.query.colors.length > 0) result.colors = this.query.colors.toString();
                if (this.query.minPrice > 0) result.minPrice = this.query.minPrice;
                if (this.query.maxPrice > 0) result.maxPrice = this.query.maxPrice;
                return result;
            }
        },
        components: {
            'card': ItemCard
        },
        methods: {
            ...mapActions({
                getProducts: 'getProductsByCategoryUrl',
                getColors: 'getAllColors'
            }),
            getRequestQuery() {
                let query = this.getQueryParams;
                query.pageSize = this.pageSize;
                return query;
            },
            applyFilters() {
                let query = this.getQueryParams;
                query.pageNum = 0;
                const path = '/products/' + this.categoryUrl;

                this.$router.push({ path, query})
            },
            loadData() {
                if (this.categoryUrl) {
                    this.getProducts({categoryUrl: this.categoryUrl, query: this.getRequestQuery()})
                        .then((pageData) => {
                            this.pageData = pageData;
                            this.setPagination();
                            this.getColors()
                                .then((data) => {
                                    this.colorsList = data;
                                })
                                .catch(() => {
                                    this.$router.push("/errors/404")
                                })
                        })
                        .catch(() => {
                            this.$router.push("/errors/404")
                        })
                }
            },
            setPagination() {
                let currentPage = this.pageData.number;
                let max = this.pageData.totalPages;
                const start = currentPage -3 >0 ? currentPage - 3 : 0;
                const end = currentPage +3 < max ? currentPage + 3 : max;


                if (start > 0) {
                    this.pages.push(this.generatePaginationObject(0, '1...', false, {...this.$route.query}));
                }

                for (let i = start; i< end; i++) {
                    this.pages.push(this.generatePaginationObject(+i, +i+1, +i === currentPage, {...this.$route.query}));
                }

                if (end !== max) {
                    this.pages.push(this.generatePaginationObject(max-1, '...' +  +max, false, {...this.$route.query}));

                }
            },
            generatePaginationObject(pageNum, name, isCurrent, query) {
                query.pageNum = pageNum;
                return {
                    url: '/products/' + this.categoryUrl,
                    name: name,
                    isCurrent: isCurrent,
                    query: query
                }
            }
        },
        created() {
            this.loadData();
        },
        beforeRouteUpdate(to, from, next) {
            this.loadData();
            next();
        }

    }
</script>

<style scoped>
    @import "../assets/css/products.css";
</style>
