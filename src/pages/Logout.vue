<template>

</template>

<script>

    import {mapMutations} from 'vuex'

    export default {
        methods: {
            ...mapMutations({
                LOGOUT: 'LOGOUT'
            }),
            logoutUser() {
                this.LOGOUT();
                this.$router.push("/login")
            }
        },
        created() {
            this.logoutUser();
        }
    }
</script>

<style scoped>

</style>
