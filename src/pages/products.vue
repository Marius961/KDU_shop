<template>
    <div class="container">
        <div class="row">
            <div class="col-3 filters">
                <div class="row filters-group">
                    <div class="col-12 group-name">Розмір</div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">XS
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">S
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">L
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">XL
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">XXL
                            <span class="checkmark"></span>
                        </label>
                    </div>
                </div>
                <div class="row filters-group">
                    <div class="col-12 group-name">Колір</div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">Чорний
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">Білий
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">Сірий
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">Червоний
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <div class="col-12 group-items">
                        <label class="checkbox-container">
                            <input type="checkbox">Фіолетовив
                            <span class="checkmark"></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="row">
                    <card
                            v-for="item in pageData.content"
                            :key="'product' + item.id"
                            class="col-12 col-sm-6 col-md-4"
                            :id="item.id"
                            :name="item.name"
                            :image-name="item.imageName"
                            :price="item.price"
                    >
                    </card>
                </div>
                <div class="row justify-content-center pagination">
                    <router-link :class="{'page-active': page.isCurrent}" v-for="page in pages" :key="'page' + page.name" :to="page.url" class="col-auto">{{page.name}}</router-link>
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
                pageData: {},
                pages: [],
            }
        },
        components: {
            'card': ItemCard
        },
        methods: {
            ...mapActions({
                getProducts: 'getProductsByCategoryUrl'
            }),
            loadData() {
                if (this.categoryUrl) {
                    const page = this.$route.query.page ? this.$route.query.page : 0;
                    this.getProducts({categoryUrl: this.categoryUrl, page})
                        .then((pageData) => {
                            this.pageData = pageData;
                            this.setPagination();
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
                    this.addPageToPagination(1, '1...', false);
                }
                for (let i = start; i< end; i++) {
                    this.pages.push({
                        url: '/products/' + this.categoryUrl + '?page=' + i,
                        name: i+1,
                        isCurrent: currentPage === i
                    })

                }
                if (end !== max) {
                    this.addPageToPagination(max-1, '...' +  +max, false);
                }
            },
            addPageToPagination(page, name, isCurrent) {
                this.pages.push({
                    url: '/products/' + this.categoryUrl + '?page=' + page,
                    name: name,
                    isCurrent: isCurrent
                })
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
