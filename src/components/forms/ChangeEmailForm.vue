<template>
    <div class="row justify-content-end">
        <form class="col-12" @submit.prevent="setNewEmail()">
            <div class="row no-gutters form-group-1"
                 :class="{'group-error': $v.email.$error }">

                <form-error :target="$v.email" param-name="minLength">Мінімум {{$v.email.$params.minLength.min}} символа</form-error>
                <form-error :target="$v.email" param-name="maxLength">Максимум {{$v.email.$params.maxLength.max}} символи</form-error>
                <form-error :target="$v.email" param-name="email" v-if="$v.email.minLength">Поле не містить емейл</form-error>
                <form-error :target="$v.email" param-name="isEmailRegistered">Користувач з таким емейлом вже зареєстрований</form-error>

                <input type="email" placeholder="Новий емейл" v-model.lazy.trim="$v.email.$model" id="email">
            </div>
            <div class="row justify-content-end no-gutters">
                <button :disabled="$v.$invalid" class="col-12 submit-btn">Змінити пароль</button>
            </div>
        </form>
    </div>
</template>

<script>
    import errorLabel from "../ErrorLabel";
    import { required, minLength, email, maxLength} from 'vuelidate/lib/validators'
    import {isEmailRegistered} from "../../validators/userValidator";
    import {mapActions} from "vuex";

    export default {
        data() {
            return {
                email: ''
            }
        },
        validations: {
            email: {required, email, minLength: minLength(4), maxLength: maxLength(32), isEmailRegistered},
        },
        components: {
            'form-error': errorLabel
        },
        methods: {
            ...mapActions({
                changeEmail: 'changeEmail'
            }),
            setNewEmail() {
                if(!this.$v.invalid) {
                    this.changeEmail({email: this.email})
                        .then(() => {
                            this.showSuccessAlert("Емейл змінено");
                            this.$emit("closeForm");
                            this.$emit("updateData");
                        })
                        .catch(() => {
                            this.showErrorMessage("Невдалось змінити емейл")
                        })
                }
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
        }
    }
</script>

<style scoped>
    @import "../../assets/css/form.css";
</style>
