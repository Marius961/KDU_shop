<template>
    <div class="container">
        <div class="row justify-content-center">
            <form @submit.prevent="sendForm()" class="col-12 col-sm-auto form form-box">
                <div class="row justify-content-center">
                    <div class="col-auto form-icon">
                        <i class="fas fa-user-plus"></i>
                    </div>
                    <div class="col h3 text-center">Реєстрація</div>
                </div>
                <div class="row no-gutters form-group-1" :class="{'group-error': $v.user.username.$error }">

                    <form-error :target="$v.user.username" param-name="minLength">Мінімум 3 символа</form-error>
                    <form-error :target="$v.user.username" param-name="maxLength">Максимум 32 символа</form-error>
                    <form-error :target="$v.user.username" param-name="isUserRegistered">Користувач вже існує</form-error>

                    <input type="text" placeholder="Логін" v-model.lazy="$v.user.username.$model" id="username">
                </div>

                <div class="row no-gutters form-group-1"
                     :class="{'group-error': $v.user.email.$error }">

                    <form-error :target="$v.user.email" param-name="minLength">Мінімум 4 символа</form-error>
                    <form-error :target="$v.user.email" param-name="maxLength">Максимум 32 символи</form-error>
                    <form-error :target="$v.user.email" param-name="email" v-if="$v.user.email.minLength">Поле не містить емейл</form-error>
                    <form-error :target="$v.user.email" param-name="isEmailRegistered">Емейл вже зареєстрований</form-error>

                    <input type="email" placeholder="Емейл" v-model.lazy="$v.user.email.$model" id="email">
                </div>

                <div class="row no-gutters form-group-1"
                     :class="{'group-error': $v.user.password.$error }">

                    <form-error :target="$v.user.password" param-name="minLength">Занадто короткий пароль</form-error>
                    <form-error :target="$v.confirmPassword" param-name="sameAs">Паролі не співпадають</form-error>

                    <input type="password" placeholder="Пароль" v-model="$v.user.password.$model" id="password">
                </div>
                <div class="row no-gutters form-group-1"
                     :class="{'group-error': $v.user.password.$error }">
                    <input type="password" placeholder="Підтвердіть пароль" v-model="$v.confirmPassword.$model" id="confirmPassword">
                </div>
                <div class="row justify-content-center no-gutters">
                    <button :disabled="$v.$invalid" class="col-12 submit-btn">Зареєструватись</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>

    import  {mapActions} from 'vuex'
    import { required, minLength, email, maxLength, sameAs} from 'vuelidate/lib/validators'
    import {isUserRegistered, isEmailRegistered} from "../validators/userValidator";
    import errorLabel from "../components/errorLabel";

    export default {
        data() {
            return {
                user: {
                    username: '',
                    email: '',
                    password: ''
                },
                isError: false,
                confirmPassword: ''
            }
        },
        validations: {
            user: {
                username: {required, minLength: minLength(4), maxLength: maxLength(16), isUserRegistered},
                email: {required, email, minLength: minLength(4), maxLength: maxLength(32), isEmailRegistered},
                password: {required, minLength: minLength(6), maxLength: maxLength(32)}
            },
            confirmPassword: {required ,sameAs: sameAs(function() { return this.user.password })}
        },
        methods: {
            ...mapActions({
                register: 'registerUser',
                loginUser: 'loginUser'
            }),
            sendForm() {
                if (!this.$v.$invalid) {
                    this.isError = false;
                    this.register({...this.user}).then((result) => {
                        console.log(result);
                        this.$router.push('/')
                    }).catch(() => {
                        this.isError = true
                    })
                }
            },
            textToLatin(text) {
                const re = /[^A-Z0-9]/gi;
                return text.replace(re, '');
            }
        },
        components: {
            'form-error': errorLabel
        },
        watch: {
            'user.username': {
                handler(newVal){
                    this.user.username = this.textToLatin(newVal);
                },
                deep: true
            },
            'confirmPassword': {
                handler(newVal){
                    this.confirmPassword = this.textToLatin(newVal);
                },
                deep: true
            },
            'user.password': {
                handler(newVal){
                    this.user.password = this.textToLatin(newVal);
                },
                deep: true
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
