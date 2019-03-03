<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">Додати категорію</div>
        </div>
        <hr class="w-100">
        <div class="row success-message mb-2 mt-1" :class="{'message-show': successMessage}">Категорію успішно додано</div>
        <div class="row error-message mb-2 mt-1" :class="{'message-show': errorMessage.isShow}">{{errorMessage.text + errorMessage.errorText}}</div>
        <div class="row justify-content-center">
            <form class="col-auto form-2 form-body" @submit.prevent="submitForm()">
                <div class="row form-group-1" :class="{'group-error': $v.category.categoryName.$error}">
                    <label for="categoryName">Назва категорії</label>
                    <input id="categoryName" v-model="$v.category.categoryName.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.category.categoryUrl.$error}">
                    <label for="categoryURL">URL адреса категорії</label>
                    <input id="categoryURL" v-model="$v.category.categoryUrl.$model" type="text">
                </div>
                <div class="row justify-content-end no-gutters">
                    <button :disabled="$v.$invalid" class="col-auto submit-btn">Додати категорію</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import { required, minLength, maxLength} from 'vuelidate/lib/validators'
    import {isNameUnique, isUrlUnique} from "../validators/categoryValidator";

    export default {
        data() {
            return {
                category: {
                    categoryName: '',
                    categoryUrl: '',
                },
                successMessage: false,
                errorMessage: {
                    isShow: false,
                    text: 'Виникла помилка: ',
                    errorText: ''
                }
            }
        },
        validations: {
            category: {
                categoryName: {required, minLength: minLength(3), maxLength: maxLength(32), isNameUnique},
                categoryUrl: {required, minLength: minLength(3), maxLength: maxLength(32), isUrlUnique}
            }
        },
        methods: {
            ...mapActions({
                addCategory: 'addCategory'
            }),
            submitForm() {
                if (!this.$v.$invalid) {
                    this.addCategory(this.category)
                        .then(() => {
                            this.clearData();
                            this.showSuccessMessage();
                        })
                        .catch((error) => {
                            if (error.status === 403) {
                                this.$router.push('/login')
                            } else {
                                this.showErrorMessage(error.data.status + " | " + error.data.error)
                            }
                        })
                }
            },
            showSuccessMessage() {
                this.successMessage = true;
                setTimeout(() => { this.successMessage = false}, 4000);
            },
            clearData() {
                this.category.categoryName = '';
                this.category.categoryUrl = '';
                this.$v.$reset()
            },
            showErrorMessage(error) {
                this.errorMessage.errorText = error;
                this.errorMessage.isShow = true;
                setTimeout(() => {
                    this.errorMessage.isShow = false;
                    this.errorMessage.errorText = '';
                }, 4000);
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
        }

    }
</script>

<style scoped>
    @import "../assets/css/form.css";
</style>
