<template>
    <div class="container">
        <div class="row">
            <header-item></header-item>
            <order-item v-for="order in pageData.content" :order="order" :key="order.id" class="col-12"></order-item>
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
</template>

<script>
    import orderItem from "../components/order/OrderListItem";
    import {mapActions} from 'vuex'
    import headerItem from "../components/order/OrdersListHeader";

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
                getOrders: 'getOrders'
            }),
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
            generatePaginationObject(pageNumber, name, isCurrent, query) {
                query.pageNumber = pageNumber;
                return {
                    url: '/orders',
                    name: name,
                    isCurrent: isCurrent,
                    query: query
                }
            }
        },
        created() {
            this.getOrders(this.query)
                .then((data) => {
                    this.pageData = data;
                    this.setPagination();
                })
                .catch(() => {
                    alert('error')
                })
        }
    }
</script>

<style scoped>
    @import "../assets/css/myOrders.css";
</style>
