<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">Додати товар</div>
        </div>
        <hr class="w-100">
        <div class="row success-message mb-2 mt-1" :class="{'message-show': successMessage}">Товар успішно створено</div>
        <div class="row error-message mb-2 mt-1" :class="{'message-show': errorMessage.isShow}">{{errorMessage.text + errorMessage.errorText}}</div>
        <div class="row justify-content-center">
            <form class="col-auto form-2 form-body" @submit.prevent="submitForm()">
                <div class="row form-group-1" :class="{'group-error': $v.product.name.$error}">
                    <label for="name">Назва</label>
                    <input id="name" v-model="$v.product.name.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.color.$error}">
                    <label for="color">Колір</label>
                    <input id="color" v-model="$v.product.color.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.description.$error}">
                    <label for="description">Опис</label>
                    <input id="description" v-model="$v.product.description.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.sizes.$error}">
                    <label for="sizes">Розміри</label>
                    <input id="sizes" v-model="$v.product.sizes.$model" type="text">
                </div>
                <div class="row form-group-1">
                    <label for="image">Фото</label>
                    <input id="image" @change="onFileChange($event)" type="file">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.price.$error}">
                    <label for="price">Ціна за шт.</label>
                    <input id="price" v-model="$v.product.price.$model" type="number">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.category.id.$error}">
                    <label for="categoryId">Категорія</label>
                    <select id="categoryId" v-model="$v.product.category.id.$model">
                        <option disabled value="">Виберіть категорію</option>
                        <option v-for="category in categories" :key="'option' + category.id" :value="category.id">{{category.categoryName}}</option>
                    </select>
                </div>
                <div class="row justify-content-end no-gutters">
                    <button :disabled="$v.$invalid" class="col-12 col-sm-auto submit-btn">Додати категорію</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import { required, minLength, maxLength} from 'vuelidate/lib/validators'

    import {mapActions, mapGetters} from "vuex";

    export default {
        computed: {
            ...mapGetters({
                categories: 'getCategories'
            })
        },
        data() {
            return {
                product: {
                    name: '',
                    color: '',
                    description: '',
                    sizes: '',
                    price: '',
                    category: {
                        id: ''
                    }
                },
                image: '',
                successMessage: false,
                errorMessage: {
                    isShow: false,
                    text: 'Виникла помилка: ',
                    errorText: ''
                }
            }
        },
        methods: {
            submitForm() {
                if (!this.$v.$invalid) {
                    let formData = new FormData();
                    formData.append("product", new Blob([JSON.stringify(this.product)],{ type: "application/json"}));
                    formData.append("image", this.image);
                    this.addProduct(formData)
                        .then(() => {
                            this.showSuccessMessage();
                        })
                        .catch((e) => {
                            this.showErrorMessage(e);
                        })
                }
            },
            onFileChange(event) {
                this.image = event.target.files[0];
            },
            ...mapActions({
                addProduct: 'postProduct'
            }),
            showSuccessMessage() {
                this.successMessage = true;
                setTimeout(() => { this.successMessage = false}, 4000);
            },
            clearData() {
                this.product.name = '';
                this.product.color = '';
                this.product.description = '';
                this.product.sizes = '';
                this.product.price = '';
                this.product.category.id = '';
                this.image = '';
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
        validations: {
            product: {
                name: {required, minLength: minLength(3), maxLength: maxLength(32)},
                color: {required, minLength: minLength(3), maxLength: maxLength(32)},
                description: {required, minLength: minLength(3), maxLength: maxLength(512)},
                sizes: {maxLength: maxLength(32)},
                price: {required},
                category: {
                    id: {required}
                }
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/form.css";
</style>
