<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6 p-0 form">
                <div class="row no-gutters form-header">
                    <div class="col-12">Реєстрація</div>
                </div>
                <div class="row no-gutters error-label" v-if="isError">
                    <div class="col-12">Сталася помилка, перезавантажте сторінку та спробуйте щераз</div>
                </div>
                <div class="row ">
                    <div class="col form-body">
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.email.$error }">
                            <label for="email" class="col-12">Емейл</label>
                            <input type="email" v-model="$v.user.email.$model" id="email">
                        </div>
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.username.$error }">
                            <label for="username" class="col-12">Логін</label>
                            <input type="text" v-model="$v.user.username.$model" id="username">
                        </div>
                        <div class="row no-gutters form-group-1"
                             :class="{'group-error': $v.user.password.$error }">
                            <label for="password" class="col-12">Пароль</label>
                            <input type="password" v-model="$v.user.password.$model" id="password">
                        </div>
                        <div class="row justify-content-center no-gutters">
                            <button :disabled="$v.$invalid" @click="sendForm()" class="col-auto submit-btn">Зареєструватись</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import  {mapActions} from 'vuex'
    import { required, minLength, email} from 'vuelidate/lib/validators'

    export default {
        data() {
            return {
                user: {
                    username: '',
                    email: '',
                    password: ''
                },
                isError: false
            }
        },
        validations: {
            user: {
                //TODO: change minLength such as in DB
                username: {required, minLength: minLength(3)},
                email: {required, email, minLength: minLength(4)},
                password: {required, minLength: minLength(6)}
            }
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
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/form.css";
</style>
