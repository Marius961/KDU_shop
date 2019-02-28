<template>
    <div class="container">
        <div class="row justify-content-center">
            <form @submit.prevent="loginUser()" class="col-5 p-0 form">
                <div class="row no-gutters form-header">
                    <div class="col-12">Вхід</div>
                </div>
                <div class="row no-gutters error-label" v-if="isError">
                    <div class="col-12">Неправильний логін або пароль</div>
                </div>
                <div class="row ">
                    <div class="col form-body">
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.username.$error }"
                        >
                            <label for="username" class="col-12">Логін</label>
                            <input class="col-12"  type="text" v-model.lazy="$v.user.username.$model" id="username">
                        </div>
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.password.$error }">
                            <label for="password" class="col-12">Пароль</label>
                            <input class="col-12"  type="password" v-model="$v.user.password.$model" id="password">
                        </div>
                        <div class="row justify-content-end no-gutters">
                            <button :disabled="$v.$invalid"  type="submit" class="col-auto submit-btn">Увійти</button>
                        </div>
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
                    this.login({...this.user}).then(() => {
                        this.$router.push('/')
                    }).catch((error) => {
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
</style>
