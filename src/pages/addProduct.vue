<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">Додати товар</div>
        </div>
        <hr class="w-100">
        <div class="row">
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
                    <button :disabled="$v.$invalid" class="col-auto submit-btn">Додати категорію</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    import { required, minLength} from 'vuelidate/lib/validators'

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
                            alert('Success')
                        })
                        .catch((e) => {
                            alert(e)
                        })
                }
            },
            onFileChange(event) {
                this.image = event.target.files[0];
            },
            ...mapActions({
                addProduct: 'postProduct'
            })
        },
        validations: {
            product: {
                name: {required, minLength: minLength(3)},
                color: {},
                description: {},
                sizes: {},
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
