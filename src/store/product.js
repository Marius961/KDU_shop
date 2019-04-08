import {$http, getAuthConfig} from "../axiosConfig";

export default  {
    actions: {
        getProduct(context, productId) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/" + productId)
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        searchProducts(context, request) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/search", {params: {...request, pageNum: 0, pageSize: 8}})
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        postProduct(context, formProduct) {
            return new Promise((resolve, reject) => {
                $http.post('/api/product', formProduct, getAuthConfig())
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        updateProduct(context, formProduct) {
            return new Promise((resolve, reject) => {
                $http.put('/api/product', formProduct, getAuthConfig())
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        deleteProduct(context, productId) {
            return new Promise((resolve, reject) => {
                $http.delete("/api/product/" + productId, getAuthConfig())
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getHomePageProducts() {
            return new Promise((resolve, reject) => {
                $http.get("/api/product?pageNum=0&pageSize=16")
                    .then((response) => {
                        resolve(response.data.content);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getAllProducts(context, query) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/", {params: query, ...getAuthConfig()})
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getProductsByCategoryUrl(context, params) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/category/"+ params.categoryUrl, {params: params.query})
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getAllColors(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/colors")
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getPopularProducts(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/popular")
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        }
    },
    mutations: {
    },
    getters: {

    }
}
