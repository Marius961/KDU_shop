<template>
    <div class="container">
        <form class="row align-items-end" @submit.prevent="submitOrderForm()">
            <div class="col-12 form-group-header mt-3 mb-3">Основна інформація</div>
            <div class="col-12 col-sm-6 col-lg-4 form-group-1" :class="{'group-error': $v.order.address.recipientFullName.$error }">
                <label for="recipientFullName">П. І. Б. отримувача</label>
                <input v-model="$v.order.address.recipientFullName.$model" type="text" id="recipientFullName">
            </div>
            <div class="col-12 col-sm-5 col-lg-3 pb-2  ">
                <form-error :target="$v.order.address.recipientFullName" param-name="minLength">Мінімум 5 символів</form-error>
                <form-error :target="$v.order.address.recipientFullName" param-name="maxLength">Максимум 64 символа</form-error>
            </div>

            <hr class="w-100 m-2">
            <div class="col-12 col-sm-6 col-md-5 col-lg-4 form-group-1" :class="{'group-error': $v.order.address.city.$error }">
                <label for="city">Місто</label>
                <input v-model="$v.order.address.city.$model" type="text" id="city">
            </div>
            <div class="col-12 col-sm-5 col-md-4 col-lg-3 pb-2  ">
                <form-error :target="$v.order.address.city" param-name="minLength">Мінімум 2 символи</form-error>
                <form-error :target="$v.order.address.city" param-name="maxLength">Максимум 64 символа</form-error>
            </div>

            <hr class="w-100 m-2">
            <div class="col-12 col-md-6 col-lg-5 form-group-1" :class="{'group-error': $v.order.address.region.$error }">
                <label for="region">Область</label>
                <input v-model="$v.order.address.region.$model" type="text" id="region">
            </div>
            <div class="col-12 col-md-4 col-lg-3 pb-2  ">
                <form-error :target="$v.order.address.region" param-name="minLength">Мінімум 2 символи</form-error>
                <form-error :target="$v.order.address.region" param-name="maxLength">Максимум 80 символів</form-error>
            </div>

            <div class="w-100"></div>
            <div class="col-12 col-sm-6 col-lg-4 form-group-1" :class="{'group-error': $v.order.address.street.$error }">
                <label for="street">Вулиця</label>
                <input v-model="$v.order.address.street.$model" type="text" id="street">
            </div>
            <div class="col-12 col-sm-6 col-lg-3 pb-2">
                <form-error :target="$v.order.address.street" param-name="minLength">Мінімум 2 символи</form-error>
                <form-error :target="$v.order.address.street" param-name="maxLength">Максимум 64 символів</form-error>
            </div>

            <hr class="w-100 m-2">
            <div class="col-12 col-sm-6 col-md-3 col-lg-2 form-group-1" :class="{'group-error': $v.order.address.buildingNumber.$error }">
                <label for="buildingNumber">Номер будинку</label>
                <input v-model="$v.order.address.buildingNumber.$model" type="text" id="buildingNumber">
            </div>
            <div class="col-12 col-sm-6 col-md-3 pb-2  ">
                <form-error :target="$v.order.address.buildingNumber" param-name="minLength">Мінімум 1 цифра</form-error>
                <form-error :target="$v.order.address.buildingNumber" param-name="maxLength">Максимум 16 цифр</form-error>
            </div>

            <div class="col-12 col-sm-6 col-md-3 col-lg-2 form-group-1" :class="{'group-error': $v.order.address.apartmentNumber.$error }">
                <label for="apartmentNumber">квартира</label>
                <input v-model="$v.order.address.apartmentNumber.$model" type="number" id="apartmentNumber">
            </div>
            <div class="col-12 col-sm-6 col-md-3 pb-2  ">
                <form-error :target="$v.order.address.apartmentNumber" param-name="minLength">Мінімум 1 цифра</form-error>
                <form-error :target="$v.order.address.apartmentNumber" param-name="maxLength">Максимум 16 цифр</form-error>
            </div>

            <hr class="w-100 m-2">
            <div class="col-12 col-md-8 form-group-1">
                <label for="postOfficeNumber">Номер поштового відділення або інша уточнююча інформація</label>
                <textarea rows="3" v-model="$v.order.address.postOfficeNumber.$model" type="text" id="postOfficeNumber"></textarea>
            </div>
            <div class="col-12 col-md-4 pb-2 align-self-center">
                <form-error :target="$v.order.address.postOfficeNumber" param-name="maxLength">Максимум 120 символів</form-error>
            </div>

            <div class="w-100"></div>
            <div class="col-12 form-group-header mt-3 mb-3">Список замовлених товарів</div>
            <div class="col-12 mt-3 mb-3">
                <div class="row mt-1 mb-1 d-none d-sm-flex">
                    <div class="col-sm-6 col-md-8 text-center font-weight-bold">Назва</div>
                    <div class="col-sm-3 col-md-2 text-center font-weight-bold">кількість</div>
                    <div class="col-sm-3 col-md-2 text-center font-weight-bold">ціна в грн.</div>
                </div>
                <div class="row">
                    <div class="col order-items">
                        <ordered-item v-for="orderedItem in orderedItems" :key="orderedItem.id" :ordered-item="orderedItem"></ordered-item>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-8 form-group-1">
                <label for="comment">Коментар до замовлення</label>
                <textarea rows="8" v-model="$v.order.comment.$model" id="comment"></textarea>
            </div>
            <div class="col-12 col-md-4 align-self-center pb-2">
                <form-error :target="$v.order.comment" param-name="maxLength">Максимум 512 символів</form-error>
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
    import errorLabel from "../components/errorLabel";
    import orderedItem from "../components/orderedItem";

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
                    apartmentNumber: {required, minLength: minLength(1), maxLength: maxLength(16)},
                    postOfficeNumber: {maxLength: maxLength(120)}
                },
                comment: {maxLength: maxLength(512)}
            }
        },
        computed: {
            ...mapGetters({
                orderedItems: 'getCartItemsList',
                totalCartPrice: 'totalCartPrice'
            })
        },
        components: {
            'cart-item' : CartItem,
            'form-error': errorLabel,
            'ordered-item': orderedItem
        },
        methods: {
            ...mapActions({
                getCart: 'getCartItems',
                createOrder: 'postOrder'
            }),
            submitOrderForm() {
                if (!this.$v.$invalid) {
                    this.order.orderedItems = this.generateOrderItems();
                    this.createOrder(this.order)
                        .then((data) => {
                            this.$swal.fire({
                                type: 'success',
                                title: 'Замовлення успішно створено!',
                                html: 'Номер Вашого замовлення: ' + data.orderId
                            }).then(() => {
                                this.$router.push("/")
                            })
                        })
                        .catch(() => {
                            this.$swal.fire({
                                type: 'error',
                                title: 'Невдалось створити замовлення!',
                                text: 'Перезавантажте сторінку та спробуйте щераз.'
                            })
                        })
                }
            },
            generateOrderItems() {
                return this.orderedItems.map(item => {
                    return {
                        product: {
                            id: item.product.id
                        },
                        quantity: item.quantity,
                        size: item.size
                    }
                })
            }
        },
        created() {
            this.getCart();
            if (!this.orderedItems.length > 0) {
                this.$router.push("/")
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/form.css";

    .order-items {
        overflow-y: scroll;
    }

    .order-items div:nth-child(even) {
        background: #e6e6e6;
    }
</style>
