<template>
    <div class="container">
        <div class="row align-items-center box">
            <div class="col-12 col-sm-6 col-md-4 account-icon">
                <i class="fas fa-user-circle"></i>
            </div>
            <div class="col-12 col-sm-6 col-md-8">
                <div class="row">
                    <div class="col-12 h4">Мій аккаунт</div>
                    <hr class="w-100 m-2">
                    <div class="col-12">
                        <div class="row">
                            <div class="col-12 h5">Ім’я користувача</div>
                            <div class="col-12">{{accountInfo.username}}</div>
                        </div>
                    </div>
                    <hr class="w-100 m-2">
                    <div class="col-12 col-md-6">
                        <div class="row">
                            <div class="col-12 h5">
                                Емейл
                                <label class="change-label" @click="isShowChangeEmailForm = true">
                                    <i class="fas fa-pen-square"></i>
                                    Змінити
                                </label>
                            </div>
                            <div class="col-12">{{accountInfo.email}}</div>
                        </div>
                    </div>
                    <hr class="w-100 m-2 d-md-none">
                    <div class="col-12 col-md-6">
                        <div class="row">
                            <div class="col-12 h5">
                                Пароль
                                <label class="change-label" @click="isShowChangePasswordForm = true">
                                    <i class="fas fa-pen-square"></i>
                                    Змінити
                                </label>
                            </div>
                            <div class="col-12">************</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row popup" v-if="isShowChangePasswordForm">
            <div class="col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 content">
                <i class="fas fa-times" @click="closeChangePasswordForm"></i>
                <change-password-form @closeForm="closeChangePasswordForm"></change-password-form>
            </div>
        </div>
        <div class="row popup" v-if="isShowChangeEmailForm">
            <div class="col-12 col-sm-6 col-md-5 col-lg-4 col-xl-3 content">
                <i class="fas fa-times" @click="closeChangeEmailForm"></i>
                <change-email-form @updateData="loadInfo()" @closeForm="closeChangeEmailForm"></change-email-form>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import ChangePassword from "../components/ChangePassword";
    import ChangeEmail from "../components/ChangeEmail";

    export default {
        data() {
            return {
                accountInfo: [],
                isShowChangePasswordForm: false,
                isShowChangeEmailForm: false,
            }
        },
        methods: {
            ...mapActions({
                getAccountInfo: 'getAccountInfo',
                changePassword: 'changePassword'
            }),
            loadInfo() {
                this.getAccountInfo()
                    .then((accountInfo) => {
                        this.accountInfo = accountInfo;
                    })
                    .catch((error) => {
                        this.showErrorMessage(
                            "Невдалось завантажити дані",
                            "Помилка: " + error
                        ).then(() => this.$router.push("/"));
                    })
            },
            showSuccessAlert(title) {
                this.$swal.fire({
                    type: 'success',
                    title,
                    showConfirmButton: false,
                    timer: 1500
                })
            },
            showErrorMessage(title, text) {
                return this.$swal.fire({
                    type: 'error',
                    title,
                    text
                })
            },
            showConfirmDialog(title, text, confirmText) {
                return this.$swal.fire({
                    title,
                    text,
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonText: confirmText,
                    cancelButtonText: 'Скасувати'
                });
            },
            closeChangePasswordForm() {
                this.isShowChangePasswordForm = false;
            },
            closeChangeEmailForm() {
                this.isShowChangeEmailForm = false;
                this.loadInfo();
            }
        },
        components: {
            'change-password-form': ChangePassword,
            'change-email-form': ChangeEmail
        },
        created() {
            this.loadInfo();
        }
    }
</script>

<style scoped>
    @import "../assets/css/account.css";
</style>
