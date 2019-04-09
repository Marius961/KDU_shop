<template>
    <div class="row align-items-end">

        <form class="col-12" @submit.prevent="setNewPassword()">
            <div class="row no-gutters form-group-1"
                 :class="{'group-error': $v.oldPassword.$error }">

                <form-error :target="$v.oldPassword" param-name="minLength">Занадто короткий пароль</form-error>
                <form-error :target="$v.oldPassword" param-name="maxLength">Максимальна кількість символів {{$v.oldPassword.$params.maxLength.max}}/</form-error>

                <input type="password" placeholder="Поточний пароль" v-model.trim="$v.oldPassword.$model" id="oldPassword">
            </div>
            <hr class="w-100">
            <div class="row no-gutters form-group-1"
                 :class="{'group-error': $v.newPassword.$error }">

                <form-error :target="$v.newPassword" param-name="minLength">Занадто короткий пароль</form-error>
                <form-error :target="$v.newPassword" param-name="maxLength">Максимальна кількість символів {{$v.newPassword.$params.maxLength.max}}/</form-error>
                <form-error :target="$v.confirmNewPassword" param-name="sameAs">Паролі не співпадають</form-error>

                <input type="password" placeholder="Новий пароль" v-model.trim="$v.newPassword.$model" id="newPassword">
            </div>
            <div class="row no-gutters form-group-1"
                 :class="{'group-error': $v.newPassword.$error }">
                <input type="password" placeholder="Підтвердіть пароль" v-model.trim="$v.confirmNewPassword.$model" id="confirmNewPassword">
            </div>
            <div class="row justify-content-end no-gutters">
                <button :disabled="$v.$invalid" class="col-12 submit-btn">Змінити пароль</button>
            </div>
        </form>
    </div>
</template>

<script>
    import errorLabel from "../ErrorLabel";
    import { required, minLength, maxLength, sameAs} from 'vuelidate/lib/validators'
    import {mapActions} from "vuex";

    export default {
        data() {
            return {
                oldPassword: '',
                newPassword: '',
                confirmNewPassword: ''
            }
        },
        validations: {
            oldPassword: {required, minLength: minLength(6), maxLength: maxLength(16)},
            newPassword: {required, minLength: minLength(6), maxLength: maxLength(16)},
            confirmNewPassword: {required ,sameAs: sameAs(function() { return this.newPassword })}
        },
        components: {
            'form-error': errorLabel
        },
        methods: {
            ...mapActions({
                changePassword: 'changePassword'
            }),
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
            setNewPassword() {
                if (!this.$v.invalid) {
                    this.changePassword({oldPassword: this.oldPassword, newPassword: this.newPassword})
                        .then(() => {
                            this.showSuccessAlert("Пароль успішно змінено");
                            this.$emit('closeForm');
                        })
                        .catch((error) => {
                            if (403 === error.response.data.status)  {
                                this.showErrorMessage("Неправильний поточний пароль")
                            } else {
                                this.showErrorMessage("Невдалось змінити пароль")
                            }
                        })
                }
            }
        }
    }
</script>

<style scoped>
    @import "../../assets/css/form.css";
</style>
