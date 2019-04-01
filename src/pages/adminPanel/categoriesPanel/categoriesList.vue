<template>
    <div class="row pl-3 pr-3">
        <div class="row">
            <div class="col-12 h2">
                <i class="fas fa-clipboard-list"></i>
                Список категорій
            </div>
        </div>
        <div class="col-12 list-item" v-for="category in categories">
            <div class="row">
                <div class="col-4">{{category.categoryName}}</div>
                <div class="col-4">{{category.categoryUrl}}</div>
                <div class="col-4">
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
                this.deleteCategoryById(id)
                    .then(() => {
                        this.showSuccessAlert("Категорію видалено")
                        this.loadCategories();
                    })
                    .catch((error) => {
                        this.showErrorMessage("Невдалось видалити категорію", error)
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
