<template>
    <div class="container">
        <div class="row text-center mt-5">
            <div class="col-12 h1">Реєстрація</div>
        </div>
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="row">
                    <div class="col-12 form">
                        <div class="row no-gutters">
                            <label for="email" class="col-12">Емейл</label>
                            <input type="email" v-model="user.email" id="email">
                        </div>
                        <div class="row no-gutters">
                            <label for="username" class="col-12">Логін</label>
                            <input type="text" v-model="user.username" id="username">
                        </div>
                        <div class="row no-gutters">
                            <label for="password" class="col-12">Пароль</label>
                            <input type="password" v-model="user.password" id="password">
                        </div>
                        <div class="row justify-content-center no-gutters">
                            <button @click="sendForm()" class="col-auto submit-btn">Зареєструватись</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import  {mapActions} from 'vuex'

    export default {
        data() {
            return {
                user: {
                    username: '',
                    email: '',
                    password: ''
                },
            }
        },
        methods: {
            ...mapActions({
                register: 'registerUser',
                loginUser: 'loginUser'
            }),
            sendForm() {
                this.register({...this.user}).then((isSuccess) => {
                    this.$router.push('/')
                })
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/registration.css";
</style>
