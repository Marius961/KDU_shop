<template>
    <div class="order-item">
        <div class="row">
            <div class="col-12 order-body">
                <div class="row">
                    <div class="col-2">{{order.id}}</div>
                    <div class="col-7">Не підтверджено</div>
                    <div class="col-3">{{order.totalPrice}}</div>
                </div>
                <div v-if="isShowDetails" class="row">
                    <hr class="w-100">
                    <div class="col-6">
                        <div class="row">
                            <div class="col-12 h4">Адреса доставки</div>
                            <div class="col-12 pt-2">
                                <div class="row">
                                    <div class="col-auto font-weight-bold">П. І. Б. отримувача</div>
                                    <div class="col-auto">{{order.address.recipientFullName}}</div>
                                </div>
                            </div>
                            <div class="col-4 pt-2">
                                <div class="row">
                                    <div class="col-12 font-weight-bold">Місто</div>
                                    <div class="col-12">{{order.address.city}}</div>
                                </div>
                            </div>
                            <div class="col-8 pt-2">
                                <div class="row">
                                    <div class="col-12 font-weight-bold">Область</div>
                                    <div class="col-12">{{order.address.region}}</div>
                                </div>
                            </div>
                            <div class="col-12 pt-2">
                                <div class="row">
                                    <div class="col-auto font-weight-bold">Вулиця</div>
                                    <div class="col-auto">{{order.address.street}}</div>
                                </div>
                            </div>
                            <div class="col-6 pt-2">
                                <div class="row">
                                    <div class="col-12 font-weight-bold">Будинок №</div>
                                    <div class="col-12">{{order.address.buildingNumber}}</div>
                                </div>
                            </div>
                            <div class="col-6 pt-2">
                                <div class="row">
                                    <div class="col-12 font-weight-bold">Квартира №</div>
                                    <div class="col-12">{{order.address.apartmentNumber}}</div>
                                </div>
                            </div>
                            <div class="col-12 pt-2">
                                <div class="row">
                                    <div class="col-12 font-weight-bold">Номер поштового відділення та інша інформація</div>
                                    <div class="col-12">{{order.address.postOfficeNumber}}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="row">
                            <div class="col-12 h4">Замовлені товари</div>

                        </div>
                        <div v-for="item in order.orderedItems" :key="'item' + item.id" class="row ordered-item">
                            <router-link :to="'/product/' + item.product.id" class="col-8">{{item.product.name + ' (' + item.size + ')'}}</router-link>
                            <div class="col-2">{{item.quantity + ' шт.'}}</div>
                            <div class="col-2">{{item.totalPrice + ' гре.'}}</div>
                        </div>
                    </div>
                    <div class="col-12">
                        <hr class="w-100">
                        <div class="row">
                            <div class="col-12 h5 font-weight-bold">Коментар до замовлення</div>
                            <p class="col-12">Коментар до замовлення</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr class="w-100 m-0 mt-2">
        <div class="row justify-content-between pr-5 align-items-center details-segment" @click="isShowDetails = !isShowDetails" :class="{'details-active': isShowDetails}">
            <div class="col-auto">
                <i class="far fa-calendar-plus"></i>
                {{new Date(order.creationDate).toLocaleString()}}
            </div>
            <div class="col text-right">
                <i class="fas fa-cart-plus"></i>
                {{order.orderedItems.length}}
            </div>
            <div class="col-auto arrow">
                <i class="fas fa-chevron-down"></i>
                Деталі
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            order: Object
        },
        data() {
            return {
                isShowDetails: false
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/orderItem.css";
</style>
