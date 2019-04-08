<template>
    <transition name="slide">
        <label v-if="isShow" class="error">
            <i class="fas fa-exclamation-circle"></i>
            <slot></slot>
        </label>
    </transition>
</template>

<script>
    export default {
        props: [
            'target',
            'paramName'
        ],
        computed: {
            isShow() {
                return !this.target[this.paramName] && this.target.$dirty
            }
        }
    }
</script>

<style scoped>
    @import "../assets/css/errorLabel.css";
</style>
