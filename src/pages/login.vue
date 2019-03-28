<template>
    <div class="container">
        <div class="row justify-content-center">
            <form @submit.prevent="loginUser()" class="col-12 col-sm-auto form">
                <div class="row justify-content-center">
                    <div class="col-auto form-icon">
<!--                        <i class="fas fa-user-plus"></i>-->
                        <i class="fas fa-sign-in-alt"></i>
                    </div>
                    <div class="col h3 text-center">Вхід</div>
                </div>
                <div class="row">
                    <div class="col form-body">
                        <div class="row no-gutters error-label" :class="{'error-label-show': isError}">
                            <div class="col-12">
                                <i class="fas fa-exclamation-circle"></i>
                                Неправильний логін або пароль
                            </div>
                        </div>
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.username.$error }"
                        >
                            <input class="col-12" placeholder="Логін"  type="text" v-model="$v.user.username.$model" id="username">
                        </div>
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.password.$error }">
                            <input class="col-12" placeholder="Пароль"  type="password" v-model="$v.user.password.$model" id="password">
                        </div>
                        <div class="row justify-content-end no-gutters">
                            <button :disabled="$v.$invalid"  type="submit" class="col-12 submit-btn">Увійти</button>
                        </div>
                    </div>
                </div>
                <hr class="w-100 m-2">
                <div class="row">
                    <div class="col-12 text-center">Ще немає аккаунту?
                        <router-link to="registration" href="#">Зареєстркйтесь</router-link>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<script>

    import {mapActions} from 'vuex'

    import { required, minLength } from 'vuelidate/lib/validators'

    export default {
        data() {
            return {
                user: {
                    username: '',
                    password: '',
                },
                isError: false
            }
        },
        validations: {
            user: {
                username: {required, minLength: minLength(4)},
                password: {required, minLength: minLength(4)}
            }
        },
        methods: {
            ...mapActions({
                login: 'loginUser'
            }),
            loginUser() {
                if (!this.$v.$invalid) {
                    this.isError = false;
                    this.login({...this.user})
                        .then(() => {
                            this.$router.push('/')
                        })
                        .catch((error) => {
                            console.log(error);
                            this.isError = true;
                        })
                }
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/form.css";

    .container {
        margin-top: 80px;
    }
</style>
