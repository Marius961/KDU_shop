<template>
    <nav class="nav-bar mb-3">
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
                                        <span>ГОЛОВНА</span>
                                    </div>
                                </router-link>
                                <div class="col-12 col-md-auto nav-link">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/t-shirt.png" alt="">
                                        <span>ПРОДУКЦІЯ</span>
                                    </div>
                                    <div class="item-dropdown">
                                        <router-link
                                                v-for="category in categories"
                                                :to="{ path: '/products/' + category.categoryUrl, query: {pageNum: 0}}"
                                                :key="'c' + category.id"
                                        >{{category.categoryName}}</router-link>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12 col-md">
                            <div class="row justify-content-end">
                                <div v-if="isAuthenticated" class="col-12 col-md-5 col-lg-4 col-xl-3 nav-link">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/user.png" alt="">
                                        <span>{{username}}</span>
                                    </div>
                                    <div class="item-dropdown">
                                        <router-link to="/add-category" v-if="isAdmin">Додати категорію</router-link>
                                        <router-link to="/add-product" v-if="isAdmin">Додати товар</router-link>
                                        <router-link to="/orders">Мої замовлення</router-link>
                                        <router-link to="/orders/manage" v-if="isAdmin">Менеджер замовлень</router-link>
                                        <a href="#" @click.prevent="logoutUser">Вийти</a>
                                    </div>
                                </div>
                                <router-link v-if="isAuthenticated"  tag="div" to="/cart" active-class="nav-link-active" class="col-12 col-sm-auto nav-link">
                                    <div class="link-content">
                                        <img src="../assets/img/nav-icons/cart.png" alt="">
                                    </div>
                                </router-link>
                                <router-link v-if="!isAuthenticated"  tag="div" to="/registration" active-class="nav-link-active" class="col-12 col-sm-auto nav-link">
                                    <div class="link-content">
                                        <i class="fas fa-user-plus"></i>
                                        <span>Реєстрація</span>
                                    </div>
                                </router-link>
                                <router-link v-if="!isAuthenticated"  tag="div" to="/login" active-class="nav-link-active" class="col-12 col-sm-auto nav-link">
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

    import {mapGetters} from 'vuex'
    import {mapMutations} from 'vuex'
    import {mapActions} from 'vuex'
    import rolesHelper from '../helpers/rolesHelper'

    export default {
        computed: {
            ...mapGetters({
                categories: 'getCategories',
                isAuthenticated: 'isAuthenticated',
                username: 'getUsername'
            }),
            isAdmin() {
                return rolesHelper.isUserHasRole('ADMIN')
            }
        },
        data() {
            return {
                isShowNavMenu: false
            }
        },
        methods: {
            ...mapMutations({
                logout: 'LOGOUT'
            }),
            ...mapActions({
                loadCategories: 'getAllCategories'
            }),
            logoutUser() {
                this.logout();
                this.$router.push('/');
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
