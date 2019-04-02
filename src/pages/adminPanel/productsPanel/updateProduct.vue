<template>
    <product-form :product-id="productId"></product-form>
</template>

<script>
    import productForm from "../../../components/productForm";

    export default {
        props: ['productId'],
        components: {
            'product-form': productForm
        }
    }
</script>

<style scoped>

</style>
