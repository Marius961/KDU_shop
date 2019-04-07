<template>
    <div class="row">
        <div class="col-12 h2">
            <i class="fas fa-clipboard-list"></i>
            Користувачі
        </div>
        <div class="col-12">
            <div class="row list">
                <div class="col-12 list-item" v-for="user in pageData.content" :key="'user' + user.id">
                    <div class="row">
                        <div class="col-3 col-md-2">{{user.id}}</div>
                        <div class="col-9 col-md">{{user.username}}</div>
                        <div class="col-12 col-md-auto">
                            <div class="row justify-content-end">
                                <div v-if="isUserHasAdminRole(user.roles)" @click="removeAdminRole(user.id)" class="col-auto" title="Дати права адміністратора">
                                    <i class="fas fa-user-plus"></i>
                                    Відкликати адмін. права
                                </div>
                                <div v-else @click="addAdminRole(user.id)" class="col-auto" title="Дати права адміністратора">
                                    <i class="fas fa-user-plus"></i>
                                    Надати адмін. права
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
                    pageSize: 16,
                },
            }
        },
        methods: {
            ...mapActions({
                getUsers: 'getAllUsers',
                setAdmin: 'setAdmin',
                removeAdmin: 'removeAdmin'
            }),
            loadList() {
                this.getUsers(this.query)
                    .then((data) => {
                        this.pageData = data;
                        this.setPagination();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Невдалось завантажити список користувачів!", "Помилка: " + error)
                    })
            },
            addAdminRole(userId) {
                this.showConfirmDialog(
                    "Ви впевнені що хочете надати права адміністратора?",
                    "Якщо ви надасте права адміністратора, користувач отримає доступ до всього функціоналу адмін. панелі, це може бути небезпечно",
                    "Надати права"
                ).then((result) => {
                    if (result.value) {
                        this.setAdmin(userId)
                            .then(() => {
                                this.showSuccessAlert("Користувачеві надано права адміністратора!")
                                this.loadList();
                            })
                            .catch((error) => {
                                this.showErrorMessage("Невдалось виконати запит.", "Помилка: " + error);
                            })
                    }
                })
            },
            removeAdminRole(userId) {
                this.showConfirmDialog(
                    "Ви впевнені що хочете відкликати права адміністратора?", "",
                    "Відкликати"
                ).then((result) => {
                    if (result.value) {
                        this.removeAdmin(userId)
                            .then(() => {
                                this.showSuccessAlert("Права адміністратора відкликано!")
                                this.loadList();
                            })
                            .catch((error) => {
                                this.showErrorMessage("Невдалось виконати запит.", "Помилка: " + error);
                            })
                    }
                })
            },
            setPagination() {
                let currentPage = this.pageData.number;
                let max = this.pageData.totalPages;
                this.pages = [];
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
            },
            showConfirmDialog(title, text, confirmText) {
                return this.$swal.fire({
                    title,
                    text,
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonText: confirmText,
                    cancelButtonText: 'Скасувати'
                });
            },
            isUserHasAdminRole(roles) {
                return roles.indexOf("ADMIN") >= 0;
            }
        },
        created() {
            this.loadList();
        }
    }
</script>

<style scoped>
    @import "../../assets/css/adminPanel.css";
</style>
