<template>
    <div class="row ">
        <div class="col">
            <div class="row">
                <div class="col-12 h2" v-if="!categoryUrl">
                    <i class="fas fa-clipboard-list"></i>
                    Додати категорію
                </div>
                <div class="col-12 h2" v-else>
                    <i class="fas fa-edit"></i>
                    Редагування категорії
                </div>
            </div>
            <hr class="w-100">
            <div class="row justify-content-center">
                <form class="col-12 col-sm-7 col-md-5 form-2" @submit.prevent="submitForm()">
                    <div class="row form-group-1" :class="{'group-error': $v.category.categoryName.$error}">
                        <label for="categoryName">Назва категорії</label>
                        <input id="categoryName" v-model="$v.category.categoryName.$model" type="text">

                        <form-error :target="$v.category.categoryName" param-name="minLength">Мінімум 3 символа</form-error>
                        <form-error :target="$v.category.categoryName" param-name="maxLength">Максимум 32 символа</form-error>
                        <form-error :target="$v.category.categoryName" param-name="isNameUnique">Категорія з таким іменем вже існує</form-error>
                    </div>
                    <div class="row form-group-1" :class="{'group-error': $v.category.categoryUrl.$error}">
                        <label for="categoryURL">URL адреса категорії</label>
                        <input id="categoryURL" v-model="$v.category.categoryUrl.$model" type="text">

                        <form-error :target="$v.category.categoryUrl" param-name="minLength">Мінімум 3 символа</form-error>
                        <form-error :target="$v.category.categoryUrl" param-name="maxLength">Максимум 32 символа</form-error>
                        <form-error :target="$v.category.categoryUrl" param-name="isUrlUnique">Такий URL вже існує вже</form-error>
                    </div>
                    <div class="row no-gutters">
                        <button :disabled="$v.$invalid" class="col-12 submit-btn">
                            <span v-if="!categoryUrl">Додати категорію</span>
                            <span v-else>Зберегти зміни</span>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import { required, minLength, maxLength} from 'vuelidate/lib/validators'
    import {isNameUnique, isUrlUnique} from "../validators/categoryValidator";
    import errorLabel from "../components/errorLabel";

    export default {
        props: {
            categoryUrl: {
                default: undefined
            }
        },
        data() {
            return {
                category: {
                    categoryName: '',
                    categoryUrl: '',
                }
            }
        },
        validations: {
            category: {
                categoryName: {required, minLength: minLength(3), maxLength: maxLength(32), isNameUnique},
                categoryUrl: {required, minLength: minLength(3), maxLength: maxLength(32), isUrlUnique}
            }
        },
        components: {
            'form-error': errorLabel
        },
        methods: {
            ...mapActions({
                addCategory: 'addCategory',
                getCategory: 'getCategoryByUrl',
                updateCategory: 'updateCategory'
            }),
            submitForm() {
                if (!this.$v.$invalid) {
                    if (!this.categoryUrl) {
                        this.addCategory(this.category)
                            .then(() => {
                                this.resetForm();
                                this.showSuccessAlert('Категорія успішно додана!');
                            })
                            .catch((error) => {
                                if (error.status === 403) {
                                    this.$router.push('/login')
                                } else {
                                    this.showErrorMessage('Невдалось додати категорію!');
                                }
                            })
                    } else {
                        this.updateCategory(this.category)
                            .then(() => {
                                this.showSuccessAlert("Категорію оновлено");
                                this.$router.push('/admin-panel/categories/list')
                            })
                            .catch((error) => {
                                this.showErrorMessage('Невдалось оновити категорію!', "Помилка: " + error);
                            })
                    }
                }
            },
            resetForm() {
                this.category.categoryName = '';
                this.category.categoryUrl = '';
                this.$v.$reset()
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
        watch: {
            'category.categoryUrl': {
                handler(newVal){
                    let re = /[^A-Z0-9]/gi;
                    this.category.categoryUrl = newVal.replace(re, '');
                },
                deep: true
            }
        },
        created() {
            if (this.categoryUrl) {
                this.getCategory(this.categoryUrl)
                    .then(data => {
                        this.category = data;
                    })
                    .catch(() => {
                        this.showErrorMessage("Невдалося завантажити категорію для оновлення")
                    })
            }
        }

    }
</script>

<style scoped>
    @import "../assets/css/form.css";
</style>
