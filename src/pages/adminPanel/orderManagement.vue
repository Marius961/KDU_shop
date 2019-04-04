<template>
    <div class="row pt-3 pb-3 panel-content">
        <div class="col-12 h2">
            <i class="fas fa-clipboard-list"></i>
            Всі замовлення
        </div>
        <div class="col">
            <div class="row">
                <header-item></header-item>
                <order-item v-for="order in pageData.content" :order="order" :key="order.id" class="col-12">
                    <div class="row justify-content-end mt-3 pl-3 pr-3">
                        <button
                                @click="confirm(order.id)"
                                v-if="!order.confirmed"
                                :disabled="order.canceled"
                                class="col-auto btn-primary-action m-0 ml-sm-2"
                        >
                            <i class="fas fa-check-circle"></i>
                            Підтвердити
                        </button>
                        <button
                                @click="setShippedOut(order.id)"
                                v-if="order.confirmed && !order.shippedOut"
                                :disabled="order.canceled"
                                class="col-auto btn-primary-action m-0 ml-sm-2"
                        >
                            <i class="fas fa-shipping-fast"></i>
                            Помітити як відправлене
                        </button>
                        <hr class="w-100 d-sm-none m-2">
                        <button @click="setStatusToCanceled(order.id)"
                                :disabled="order.canceled"
                                class="col-auto btn-cancel m-0 ml-sm-2"
                        >
                            <i class="fas fa-strikethrough"></i>
                            Скасувати
                        </button>
                        <hr class="w-100">
                    </div>
                </order-item>
            </div>
            <div class="row justify-content-center pagination">
                <router-link
                        v-for="page in pages"
                        :class="{'page-active': page.isCurrent}"
                        :key="'page' + page.name"
                        :to="{ path: page.url, query: page.query }" class="col-auto"
                >
                    {{page.name}}
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
    import orderItem from "../../components/orderItem";
    import {mapActions} from 'vuex'
    import headerItem from "../../components/headerItem";

    export default {
        data() {
            return {
                pageData: {},
                query: {
                    pageSize: 8,
                    pageNumber: this.$route.query.pageNumber ? this.$route.query.pageNumber : 0,
                },
                pages: []
            }
        },
        components: {
            "order-item": orderItem,
            "header-item": headerItem
        },
        methods: {
            ...mapActions({
                getOrders: 'getAllOrders',
                cancelOrder: 'cancelOrder',
                confirmOrder: 'confirmOrder',
                markAsShippedOut: 'martAsShippedOut'
            }),
            setPagination() {
                let currentPage = this.pageData.number;
                let max = this.pageData.totalPages;
                const start = currentPage -3 >0 ? currentPage - 3 : 0;
                const end = currentPage +3 < max ? currentPage + 3 : max;
                this.pages = [];
                if (start > 0)
                    this.pages.push(this.generatePaginationObject(0, '1...', false, {...this.$route.query}));

                for (let i = start; i< end; i++) {
                    this.pages.push(this.generatePaginationObject(+i, +i+1, +i === currentPage, {...this.$route.query}));
                }

                if (end !== max) {
                    this.pages.push(this.generatePaginationObject(max-1, '...' +  +max, false, {...this.$route.query}));

                }
            },
            generatePaginationObject(pageNumber, name, isCurrent, query) {
                query.pageNumber = pageNumber;
                return {
                    url: '/orders',
                    name: name,
                    isCurrent: isCurrent,
                    query: query
                }
            },
            setStatusToCanceled(id) {
                this.cancelOrder(id)
                    .then(() => {
                        this.showSuccessAlert("Замовлення успішно скасовано!");
                        this.loadOrders();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Не вдалося скасувати замовлення", error);
                    })
            },
            confirm(id) {
                this.confirmOrder(id)
                    .then(() => {
                        this.showSuccessAlert("Замовлення успішно підтверджено!");
                        this.loadOrders();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Не вдалося підтвердити замовлення", error);
                    })
            },
            setShippedOut(id) {
                this.markAsShippedOut(id)
                    .then(() => {
                        this.showSuccessAlert("Замовлення позначено як відправлене!");
                        this.loadOrders();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Не вдалося підтвердити замовлення", error);

                    })
            },
            loadOrders() {
                this.getOrders(this.query)
                    .then((data) => {
                        this.pageData = data;
                        this.setPagination();
                    })
                    .catch(() => {
                        alert('error')
                    })
            },
            showSuccessAlert(title) {
                this.$swal.fire({
                    type: 'success',
                    title,
                    showConfirmButton: false,
                    timer: 1500
                })
            },
            showErrorMessage(title, text) {
                this.$swal.fire({
                    type: 'error',
                    title,
                    showConfirmButton: false,
                    timer: 1500
                })
            }
        },
        created() {
            this.loadOrders();
        }
    }
</script>

<style scoped>
    @import "../../assets/css/orderManagement.css";
    @import "../../assets/css/adminPanel.css";

</style>
