import {$http} from "../axiosConfig";
import JwtHelper from '../helpers/jwtHelper'

function getConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}


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
        postProduct(context, formProduct) {
            return new Promise((resolve, reject) => {
                $http.post('/api/product', formProduct, getConfig())
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
                $http.put('/api/product', formProduct, getConfig())
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
                $http.delete("/api/product/" + productId, getConfig())
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
                $http.get("/api/product/", {params: query, ...getConfig()})
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
        }
    },
    mutations: {
    },
    getters: {

    }
}
