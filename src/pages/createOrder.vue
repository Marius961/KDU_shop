<template>
    <div class="container">
        <form class="row">
            <div class="col-12 form-group-header mt-3 mb-3">Основна інформація</div>
            <div class="col-4 form-group-1" :class="{'group-error': $v.order.address.recipientFullName.$error }">
                <label for="recipientFullName">П. І. Б. отримувача</label>
                <input v-model="$v.order.address.recipientFullName.$model" type="text" id="recipientFullName">
            </div>
            <hr class="w-100 m-2">
            <div class="col-3 form-group-1" :class="{'group-error': $v.order.address.city.$error }">
                <label for="city">Місто</label>
                <input v-model="$v.order.address.city.$model" type="text" id="city">
            </div>
            <div class="col-3 form-group-1" :class="{'group-error': $v.order.address.region.$error }">
                <label for="region">Область</label>
                <input v-model="$v.order.address.region.$model" type="text" id="region">
            </div>
            <div class="w-100"></div>
            <div class="col-2 form-group-1" :class="{'group-error': $v.order.address.street.$error }">
                <label for="street">Вулиця</label>
                <input v-model="$v.order.address.street.$model" type="text" id="street">
            </div>
            <div class="col-2 form-group-1" :class="{'group-error': $v.order.address.buildingNumber.$error }">
                <label for="buildingNumber">будинок</label>
                <input v-model="$v.order.address.buildingNumber.$model" type="text" id="buildingNumber">
            </div>
            <div class="col-2 form-group-1" :class="{'group-error': $v.order.address.apartmentNumber.$error }">
                <label for="apartmentNumber">квартира</label>
                <input v-model="$v.order.address.apartmentNumber.$model" type="number" id="apartmentNumber">
            </div>
            <hr class="w-100 m-2">

            <div class="col-5 form-group-1">
                <label for="postOfficeNumber">Номер поштового відділення або інша уточнююча інформація</label>
                <input v-model="order.address.postOfficeNumber" type="text" id="postOfficeNumber">
            </div>
            <div class="w-100"></div>
            <div class="col-12 form-group-header mt-3 mb-3">Список замовлених товарів</div>
            <div class="col-12 mt-3 mb-3">
                <div class="row mt-1 mb-1">
                    <div class="col-8 text-center font-weight-bold">Назва</div>
                    <div class="col-2 text-center font-weight-bold">кількість</div>
                    <div class="col-2 text-center font-weight-bold">ціна в грн.</div>
                </div>
                <div class="row mt-1 mb-1" v-for="cartItem in cartItems" :key="cartItem.id">
                    <div class="col-8 text-center">{{cartItem.product.name}}</div>
                    <div class="col-2 text-center">{{cartItem.quantity}}</div>
                    <div class="col-2 text-center">{{cartItem.quantity * cartItem.product.price}}</div>
                </div>
            </div>
            <div class="col-8 form-group-1">
                <label for="comment">Коментар до замовлення</label>
                <textarea rows="8" v-model="order.comment" id="comment"></textarea>
            </div>
            <div class="col-12">
                <div class="row mt-5 mb-5">
                    <button :disabled="$v.$invalid" class="col-auto submit-btn">Створити замовлення</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";
    import CartItem from "../components/CartItem";
    import { required, minLength, maxLength} from 'vuelidate/lib/validators';

    export default {
        data() {
            return {
                order: {
                    orderedItems: [],
                    address: {
                        recipientFullName: '',
                        city: '',
                        region: '',
                        street: '',
                        buildingNumber: '',
                        apartmentNumber: '',
                        postOfficeNumber: ''
                    },
                    comment: ''
                }
            }
        },
        validations: {
            order: {
                orderedItems: [],
                address: {
                    recipientFullName: {required, minLength: minLength(5), maxLength: maxLength(64)},
                    city: {required, minLength: minLength(2), maxLength: maxLength(64)},
                    region: {required, minLength: minLength(2), maxLength: maxLength(80)},
                    street: {required, minLength: minLength(2), maxLength: maxLength(64)},
                    buildingNumber: {required, minLength: minLength(1), maxLength: maxLength(16)},
                    apartmentNumber: {required}
                },
                comment: ''
            }
        },
        computed: {
            ...mapGetters({
                cartItems: 'getCartItemsList',
                totalCartPrice: 'totalCartPrice'
            })
        },
        components: {
            'cart-item' : CartItem
        },
        methods: {
            ...mapActions({
                getCart: 'getCartItems',
            })
        },
        created() {
            this.getCart();
        }
    }
</script>

<style scoped>
    @import "../assets/css/form.css";
</style>
