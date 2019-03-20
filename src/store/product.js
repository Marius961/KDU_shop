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
                    .catch(() => {
                        reject();
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
                        reject(error.response);
                    })
            })
        },
        getAllProducts() {
            return new Promise((resolve, reject) => {
                $http.get("/api/product?page=0&size=16")
                    .then((response) => {
                        resolve(response.data.content);
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        getProductsByCategoryUrl(context, params) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/category/"+ params.categoryUrl, {params: params.query})
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        getAllColors(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product/colors")
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch(() => {
                        reject();
                    })
            })
        }
    },
    mutations: {
    },
    getters: {

    }
}
