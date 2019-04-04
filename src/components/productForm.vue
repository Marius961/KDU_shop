<template>
    <div class="row">
        <div class="col">
            <div class="row">
                <div class="col-12 h2" v-if="!productId">
                    <i class="far fa-plus-square"></i>
                    Додати товар
                </div>
                <div class="col-12 h2" v-else>
                    <i class="fas fa-edit"></i>
                    Редагування товару
                </div>
            </div>
            <hr class="w-100">
            <form class="row form-2" @submit.prevent="submitForm()">
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="row form-group-1" :class="{'group-error': $v.product.name.$error}">
                        <label for="name">Назва</label>
                        <input id="name" v-model.lazy.trim="$v.product.name.$model" type="text">

                        <form-error :target="$v.product.name" param-name="minLength">Мінімум {{$v.product.name.$params.minLength.min}} символа</form-error>
                        <form-error :target="$v.product.name" param-name="maxLength">Максимум {{$v.product.name.$params.maxLength.max}} символа</form-error>
                        <form-error :target="$v.product.name" param-name="isProductExist">Продукт з таким іменем та кольором вже існує</form-error>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="row form-group-1" :class="{'group-error': $v.product.color.$error}">
                        <label for="color">Колір</label>
                        <input id="color" v-model.lazy.trim="$v.product.color.$model" type="text">

                        <form-error :target="$v.product.color" param-name="maxLength">Максимум {{$v.product.color.$params.maxLength.max}} символа</form-error>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="row form-group-1" :class="{'group-error': $v.product.sizes.$error}">
                        <label for="sizes">Розміри</label>
                        <input id="sizes" v-model.trim="$v.product.sizes.$model" type="text">

                        <form-error :target="$v.product.sizes" param-name="minLength">Мінімум {{$v.product.sizes.$params.minLength.min}} символа</form-error>
                        <form-error :target="$v.product.sizes" param-name="maxLength">Максимум {{$v.product.sizes.$params.maxLength.max}} символа</form-error>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="row form-group-1">
                        <label for="image">Фото</label>
                        <div class="w-100"></div>
                        <input id="image" ref="imageInput" @change="onFileChange($event)" type="file">
                        <label class="file-selected" v-if="image !== ''">Файл вибраний</label>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="row form-group-1" :class="{'group-error': $v.product.price.$error}">
                        <label for="price">Ціна за шт.</label>
                        <input id="price" v-model.trim="$v.product.price.$model" type="number">

                        <form-error :target="$v.product.price" param-name="required">Вкажіть ціну</form-error>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="row form-group-1" :class="{'group-error': $v.product.category.id.$error}">
                        <label for="categoryId">Категорія</label>
                        <select id="categoryId" v-model="$v.product.category.id.$model">
                            <option disabled value="">Виберіть категорію</option>
                            <option v-for="category in categories" :key="'option' + category.id" :value="category.id">{{category.categoryName}}</option>
                        </select>
                        <form-error :target="$v.product.category.id" param-name="required">Оберіть категорію</form-error>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-xl-6">
                    <div class="row form-group-1" :class="{'group-error': $v.product.description.$error}">
                        <label for="description">Опис</label>
                        <textarea id="description" rows="6" v-model.trim="$v.product.description.$model" type="text"></textarea>

                        <form-error :target="$v.product.description" param-name="maxLength">Максимум {{$v.product.description.$params.maxLength.max}} символа</form-error>
                    </div>
                </div>
                <div class="col-12">
                    <div class="row justify-content-end no-gutters">
                        <button :disabled="$v.$invalid" class="col-12 col-sm-6 col-lg-4 submit-btn">
                            <span v-if="!productId">Додати товар</span>
                            <span v-else>Зберегти зміни</span>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import { required, requiredIf, minLength, maxLength} from 'vuelidate/lib/validators'

    import {mapActions, mapGetters} from "vuex";
    import errorLabel from "../components/errorLabel";
    import {checkProduct} from "../validators/productValidator";

    export default {
        props: {
            productId: {
                default: undefined
            }
        },
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
                    let formProduct = new FormData();
                    formProduct.append("product", new Blob([JSON.stringify(this.product)],{ type: "application/json"}));
                    this.image ? formProduct.append("image", this.image) : null;
                    if (!this.productId) {
                        this.addProduct(formProduct)
                            .then(() => {
                                this.showSuccessAlert('Продукт успішно додано!');
                                this.resetForm();
                            })
                            .catch(() => {
                                this.showErrorMessage('Невдалось додати продукт!');
                            })
                    } else {
                        this.updateProduct(formProduct)
                            .then(() => {
                                this.showSuccessAlert('Продукт успішно оновлено!');
                                this.resetForm();
                            })
                            .catch((error) => {
                                this.showErrorMessage('Невдалось оновити продукт!', 'Помилка: ' + error);
                            })
                    }
                }
            },
            onFileChange(event) {
                this.image = event.target.files[0];
            },
            ...mapActions({
                addProduct: 'postProduct',
                getProduct: 'getProduct',
                updateProduct: 'updateProduct'
            }),
            showSuccessAlert(title) {
                this.$swal.fire({
                    type: 'success',
                    title,
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
            resetForm() {
                this.product = {name: '', color: '', description: '', sizes: '', price: '', category: {id: ''}};
                this.image = '';
                const imageInput = this.$refs.imageInput;
                imageInput.type = 'text';
                imageInput.type = 'file';
                this.$v.$reset();
            }
        },
        validations: {
            product: {
                name: {required, minLength: minLength(3), maxLength: maxLength(128), isProductExist: function() {
                        return checkProduct(this.product.name, this.product.color);
                    }},
                color: {maxLength: maxLength(32)},
                description: {maxLength: maxLength(512)},
                sizes: {minLength: minLength(1), maxLength: maxLength(128)},
                price: {required},
                category: {
                    id: {required}
                }
            },
            image: {requiredIf: requiredIf(function () {
                    return this.productId === undefined;
                })}

        },
        components: {
            'form-error': errorLabel
        },
        created() {
            if (this.productId) {
                this.getProduct(this.productId)
                    .then((data) => {
                        this.product = data;
                    })
                    .catch((error) => {
                        this.showErrorMessage("Невдалось завантажити продукт!", "Помилка: " + error)
                    })
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/form.css";
    .container {
        margin-top: 40px;
        margin-bottom: 40px;
    }


</style>
