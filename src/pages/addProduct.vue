<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">
                <i class="fas fa-clipboard-list"></i>
                Додати товар
            </div>
        </div>
        <hr class="w-100">
        <div class="row justify-content-center">
            <form class="col-12 col-sm-6 form-2" @submit.prevent="submitForm()">
                <div class="row form-group-1" :class="{'group-error': $v.product.name.$error}">
                    <label for="name">Назва</label>
                    <input id="name" v-model.lazy="$v.product.name.$model" type="text">

                    <form-error :target="$v.product.name" param-name="minLength">Мінімум 3 символа</form-error>
                    <form-error :target="$v.product.name" param-name="maxLength">Максимум 32 символа</form-error>
                    <form-error :target="$v.product.name" param-name="isProductExist">Продукт з таким іменем та кольором вже існує</form-error>
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.color.$error}">
                    <label for="color">Колір</label>
                    <input id="color" v-model.lazy="$v.product.color.$model" type="text">

                    <form-error :target="$v.product.color" param-name="minLength">Мінімум 3 символа</form-error>
                    <form-error :target="$v.product.color" param-name="maxLength">Максимум 32 символа</form-error>
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.description.$error}">
                    <label for="description">Опис</label>
                    <input id="description" v-model="$v.product.description.$model" type="text">

                    <form-error :target="$v.product.description" param-name="minLength">Мінімум 3 символа</form-error>
                    <form-error :target="$v.product.description" param-name="maxLength">Максимум 512 символа</form-error>
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.sizes.$error}">
                    <label for="sizes">Розміри</label>
                    <input id="sizes" v-model="$v.product.sizes.$model" type="text">

                    <form-error :target="$v.product.sizes" param-name="minLength">Мінімум 3 символа</form-error>
                    <form-error :target="$v.product.sizes" param-name="maxLength">Максимум 128 символа</form-error>
                </div>
                <div class="row form-group-1">
                    <label for="image">Фото</label>
                    <div class="w-100"></div>
                    <label class="file-selected" v-if="image !== ''">Файл вибраний</label>
                    <input id="image" @change="onFileChange($event)" type="file">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.price.$error}">
                    <label for="price">Ціна за шт.</label>
                    <input id="price" v-model="$v.product.price.$model" type="number">

                    <form-error :target="$v.product.price" param-name="required">Вкажіть ціну</form-error>
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.category.id.$error}">
                    <label for="categoryId">Категорія</label>
                    <select id="categoryId" v-model="$v.product.category.id.$model">
                        <option disabled value="">Виберіть категорію</option>
                        <option v-for="category in categories" :key="'option' + category.id" :value="category.id">{{category.categoryName}}</option>
                    </select>
                    <form-error :target="$v.product.category.id" param-name="required">Будь ласка оберіть категорію</form-error>
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
    import errorLabel from "../components/errorLabel";
    import {checkProduct} from "../validators/productValidator";

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
                image: ''
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
                            this.showSuccessAlert()
                        })
                        .catch((e) => {
                            this.showErrorMessage();
                        })
                }
            },
            onFileChange(event) {
                this.image = event.target.files[0];
            },
            ...mapActions({
                addProduct: 'postProduct'
            }),
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
            showSuccessAlert() {
                this.$swal.fire({
                    type: 'success',
                    title: 'Категорія успішно додана!',
                })
            },
            showErrorMessage() {
                this.$swal.fire({
                    type: 'error',
                    title: 'Невдалось додати категорію!',
                })
            },

        },
        validations: {
            product: {
                name: {required, minLength: minLength(3), maxLength: maxLength(32), isProductExist: function() {
                        return checkProduct(this.product.name, this.product.color);
                    }},
                color: {required, minLength: minLength(3), maxLength: maxLength(32)},
                description: {required, minLength: minLength(3), maxLength: maxLength(512)},
                sizes: {minLength: minLength(1), maxLength: maxLength(128)},
                price: {required},
                category: {
                    id: {required}
                }
            },
            image: {required}
        },
        components: {
            'form-error': errorLabel
        },
    }
</script>

<style scoped>
    @import "../assets/css/form.css";
    .container {
        margin-top: 40px;
    }


</style>
