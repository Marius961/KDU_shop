<template>
    <div class="container">
        <div class="row">
            <div class="col-12 h2">Додати категорію</div>
        </div>
        <hr class="w-100">
        <div class="row">
            <form class="col-auto form-2 form-body">
                <div class="row form-group-1" :class="{'group-error': $v.categoryName.$error}">
                    <label for="categoryName">Назва категорії</label>
                    <input id="categoryName" v-model="$v.categoryName.$model" type="text">
                </div>
                <div class="row form-group-1" :class="{'group-error': $v.categoryUrl.$error}">
                    <label for="categoryURL">URL адреса категорії</label>
                    <input id="categoryURL" v-model="$v.categoryUrl.$model" type="text">
                </div>
                <div class="row justify-content-end no-gutters">
                    <button :disabled="$v.$invalid" class="col-auto submit-btn">Додати категорію</button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>

    import { required, minLength} from 'vuelidate/lib/validators'

    export default {
        data() {
            return {
                categoryName: '',
                categoryUrl: ''
            }
        },
        validations: {
            categoryName: {required, minLength: minLength(3)},
            categoryUrl: {required, minLength: minLength(3)}
        }
        //TODO: add vuex module
    }
</script>

<style scoped>
    @import "../assets/css/form.css";
</style>
