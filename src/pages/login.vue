<template>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6 form">
                <div class="row no-gutters">
                    <div class="col-12 h3">Вхід</div>
                </div>
                <div class="row no-gutters" v-if="isError">
                    <label class="col-12 error-label">Неправильний логін або пароль</label>
                </div>
                <div class="row no-gutters">
                    <label for="username" class="col-12">Логін</label>
                    <input id="username" type="text" v-model="user.username" class="col-12">
                </div>
                <div class="row no-gutters">
                    <label for="password" class="col-12">Пароль</label>
                    <input id="password" type="password" v-model="user.password" class="col-12">
                </div>
                <div class="row no-gutters">
                    <div @click="loginUser()" class="col-auto submit-btn">Відправити</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

    import {mapActions} from 'vuex'

    export default {
        data() {
            return {
                user: {
                    username: '',
                    password: ''
                },
                isError: false
            }
        },
        methods: {
            ...mapActions({
                login: 'login'
            }),
            loginUser() {
                this.login({...this.user}).then((isSuccess) => {
                    alert(isSuccess)
                    if (isSuccess) {
                        this.$router.push('/')
                    }  else {
                        this.isError = true;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/registration.css";
</style>
