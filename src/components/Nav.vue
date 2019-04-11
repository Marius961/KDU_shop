<template>
    <nav class="nav-bar">
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-auto logo">
                    <img src="../assets/img/nav-icons/KDU_logo.png" alt="">
                </div>
                <div
                        class="col-auto nav-link d-md-none"
                        @click="isShowNavMenu = !isShowNavMenu"
                >
                    <div class="link-content">
                        <img src="../assets/img/nav-icons/menu.png" alt="">
                    </div>
                </div>
                <div
                        class="col-12 col-md d-md-block menu"
                        :class="{'show-menu': isShowNavMenu}"
                >
                    <div class="row">
                        <div class="col-12 col-md-auto p-0 pr-md-3">
                            <div class="row no-gutters">
                                <router-link tag="div" to="/" active-class="nav-link-active" exact class="col-12 col-auto col-md-auto nav-link">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/home.png" alt="">
                                        <span class="d-md-none d-lg-inline">ГОЛОВНА</span>
                                    </div>
                                </router-link>
                                <div class="col-12 col-md nav-link">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/t-shirt.png" alt="">
                                        <span>ПРОДУКЦІЯ</span>
                                    </div>
                                    <div class="item-dropdown">
                                        <router-link
                                                v-for="category in categories"
                                                :to="{ path: '/products/' + category.categoryUrl, query: {pageNum: 0}}"
                                                :key="'c' + category.id"
                                        >
                                            {{category.categoryName}}
                                        </router-link>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md">
                            <div class="row justify-content-end">
                                <div class="col-12 col-md col-md-5 col-xl-4 search-field order-last order-md-1">
                                    <div>
                                        <label for="search">
                                            <i class="fas fa-search"></i>
                                        </label>
                                        <input placeholder="Пошук" @input="sendSearchRequest($event.target.value)" type="search" name="search" id="search">
                                        <div class="item-dropdown search-results" v-if="searchedProducts">
                                            <router-link tag="div" :to="'/product/' + product.id" :key="'sp' + product.id" class="row search-result no-gutters" v-for="product in searchedProducts">
                                                <div class="col">{{product.name}}</div>
                                                <div class="col-auto font-weight-bold">{{product.price}} грн</div>
                                            </router-link>
                                        </div>
                                    </div>
                                </div>
                                <div v-if="isAuthenticated" class="col-12 col-md-5 col-lg-4 col-xl-3 nav-link order-md-2">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/user.png" alt="">
                                        <span>{{username}}</span>
                                    </div>
                                    <div class="item-dropdown">
                                        <router-link to="/account">Мій аккаунт</router-link>
                                        <router-link to="/admin-panel" v-if="isAdmin">Адмін панель</router-link>
                                        <router-link to="/orders">Мої замовлення</router-link>
                                        <a href="#" @click.prevent="logoutUser">Вийти</a>
                                    </div>
                                </div>
                                <router-link v-if="isAuthenticated"  tag="div" to="/cart" active-class="nav-link-active" class="col-12 col-md-auto nav-link order-md-3">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/cart.png" alt="">
                                        <span class="d-md-none">Кошик</span>
                                    </div>
                                </router-link>
                                <router-link v-if="!isAuthenticated"  tag="div" to="/registration" active-class="nav-link-active" class="col-12 col-sm-auto nav-link order-md-2">
                                    <div class="link-content">
                                        <i class="fas fa-user-plus"></i>
                                        <span>Реєстрація</span>
                                    </div>
                                </router-link>
                                <router-link v-if="!isAuthenticated"  tag="div" to="/login" active-class="nav-link-active" class="col-12 col-sm-auto nav-link order-md-3">
                                    <div class="link-content">
                                        <i class="fas fa-sign-in-alt"></i>
                                        <span>Вхід</span>
                                    </div>
                                </router-link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>

    import {mapGetters, mapMutations, mapActions} from 'vuex'

    export default {
        computed: {
            ...mapGetters({
                categories: 'getCategories',
                isAuthenticated: 'isAuthenticated',
                username: 'getUsername',
                isAdmin: 'isAdmin'
            }),
        },
        data() {
            return {
                isShowNavMenu: false,
                searchedProducts: []
            }
        },
        methods: {
            ...mapMutations({
                logout: 'LOGOUT'
            }),
            ...mapActions({
                loadCategories: 'getAllCategories',
                search: 'searchProducts'
            }),
            logoutUser() {
                this.logout();
                this.$router.push('/');
            },
            sendSearchRequest(searchPhrase) {
                if (!searchPhrase) {
                    this.searchedProducts = [];
                } else {
                    this.search({q: searchPhrase})
                        .then((data) => {
                            this.searchedProducts = data.content;
                        })
                        .catch(() => {

                        })
                }
            }
        },
        created() {
            this.loadCategories()
                .then(() => {
                    if (this.categories.length < 1) {
                        this.$router.push('/errors/404')
                    }
                })
                .catch(() => {
                    this.$router.push('/errors/404')
                })
        }
    }
</script>

<style scoped>
    @import "../assets/css/nav.css";
</style>
