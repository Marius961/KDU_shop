<template>
    <div class="row pl-3 pr-3"">
        <div class="col-12 h2">
            <i class="fas fa-clipboard-list"></i>
            Список категорій
        </div>
        <div class="col-12">
            <div class="row list">
                <div class="col-12 list-item" v-for="product in pageData.content">
                    <div class="row">
                        <router-link :to="'/product/' + product.id" class="col-4">{{product.name}}</router-link>
                        <div class="col-4">{{product.price}}</div>
                        <div class="col-4">
                            <div class="row justify-content-end">
                                <router-link :to="'/admin-panel/products/update/' + product.id" tag="div" class="col-auto">
                                    <i class="fas fa-edit"></i>
                                </router-link>
                                <div class="col-auto" @click="deleteProduct(product.id)">
                                    <i class="fas fa-minus-square"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 mt-3">
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
</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        data() {
            return {
                pageData: {},
                pages: [],
                query: {
                    pageNum: this.$route.query.pageNum ? this.$route.query.pageNum : 0,
                    pageSize: 4,
                },
            }
        },
        methods: {
            ...mapActions({
                getProducts: "getAllProducts",
                deleteProductById: 'deleteProduct'
            }),
            loadList() {
                this.getProducts(this.query)
                    .then((data) => {
                        this.pageData = data;
                        this.setPagination();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Невдалось завантажити список товарів!", "Помилка: " + error)
                    })
            },
            deleteProduct(id) {
                this.deleteProductById(id)
                    .then(() => {
                        this.showSuccessAlert("Продукт успішно видалено!")
                        this.loadList();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Невдалось видалити продукт.", "Помилка: " + error);
                    })
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
                    url: '/admin-panel/products/list',
                    name: name,
                    isCurrent: isCurrent,
                    query: query
                }
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
                    text
                })
            }
        },
        created() {
            this.loadList();
        }
    }
</script>

<style scoped>
    @import "../../../assets/css/adminPanel.css";
</style>
