<template>
    <category-form :category-url="categoryUrl"></category-form>
</template>

<script>
    import categoryForm from "../../../components/categoryForm";

    export default {
        props: ['categoryUrl'],
        components: {
            'category-form': categoryForm
        }
    }
</script>

<style scoped>

</style>
