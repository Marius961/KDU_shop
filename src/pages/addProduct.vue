<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">Додати товар</div>
        </div>
        <hr class="w-100">
        <div class="row">
            <form class="col-auto form-2 form-body" @submit.prevent="submitForm()">
                <div class="row form-group-1" :class="{'group-error': $v.product.productName.$error}">
                    <label for="name">Назва</label>
                    <input id="name" v-model="$v.product.productName.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.color.$error}">
                    <label for="color">Колір</label>
                    <input id="color" v-model="$v.product.color.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.description.$error}">
                    <label for="description">Опис</label>
                    <input id="description" v-model="$v.product.description.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.product.productSizes.$error}">
                    <label for="productSizes">Розміри</label>
                    <input id="productSizes" v-model="$v.product.productSizes.$model" type="text">
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
                    <input id="categoryId" v-model="$v.product.category.id.$model" type="number">
                </div>
                <div class="row justify-content-end no-gutters">
                    <button :disabled="$v.$invalid" class="col-auto submit-btn">Додати категорію</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import { required, minLength} from 'vuelidate/lib/validators'

    import {mapActions} from "vuex";

    export default {
        data() {
            return {
                product: {
                    productName: '',
                    color: '',
                    description: '',
                    productSizes: '',
                    image: '',
                    price: '',
                    category: {
                        id: ''
                    }
                }
            }
        },
        methods: {
            submitForm() {
                if (!this.$v.$invalid) {
                    this.addProduct(this.product)
                        .then(() => {
                            alert('Success')
                        })
                        .catch((e) => {
                            alert(e)
                        })
                }
            },
            onFileChange(event) {
                this.product.image = event.target.files[0]
                console.log(this.product.image)
            },
            ...mapActions({
                addProduct: 'postProduct'
            })
        },
        validations: {
            product: {
                productName: {required, minLength: minLength(3)},
                color: {},
                description: {},
                productSizes: {},
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
