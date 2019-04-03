<template>
    <div class="row">
        <div class="col-12 h2">
            <i class="fas fa-clipboard-list"></i>
            Список категорій
        </div>
        <div class="col-12">
            <div class="row list">
                <div class="col-12 list-item" v-for="category in categories">
                    <div class="row">
                        <div class="col-12 col-lg-5">
                            <span class="font-weight-bold d-lg-none">Назва: </span>
                            <span>{{category.categoryName}}</span>
                        </div>
                        <div class="col-12 col-lg-5">
                            <span class="font-weight-bold d-lg-none">URL: </span>
                            <span>{{category.categoryUrl}}</span>
                        </div>
                        <div class="col">
                            <div class="row justify-content-end">
                                <router-link :to="'/admin-panel/categories/update/' + category.categoryUrl" tag="div" class="col-auto">
                                    <i class="fas fa-edit"></i>
                                </router-link>
                                <div class="col-auto" @click="deleteCategory(category.id)">
                                    <i class="fas fa-minus-square"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";

    export default {
        computed: {
            ...mapGetters({
                categories: 'getCategories'
            })
        },
        methods: {
            ...mapActions({
                loadCategories: 'getAllCategories',
                deleteCategoryById: 'deleteCategory'
            }),
            deleteCategory(id) {
                this.showConfirmDialog(
                    "Ви впевнені що хочете видалити категорію?",
                    "Якщо у категорії присутні товари, її не можливо видалити.",
                    "Спробувати видалити"
                ).then((result) => {
                    if (result.value) {
                        this.deleteCategoryById(id)
                            .then(() => {
                                this.showSuccessAlert("Категорію видалено");
                                this.loadCategories();
                            })
                            .catch((error) => {
                                this.showErrorMessage("Невдалось видалити категорію", error)
                            })
                    }
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
            }
        },
        created() {
            this.loadCategories();
        }
    }
</script>

<style scoped>
    @import "../../../assets/css/adminPanel.css";

</style>
