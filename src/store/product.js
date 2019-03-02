import {$http} from "../axiosConfig";
import JwtHelper from '../helpers/jwtHelper'

const config ={
    headers: {
        ...JwtHelper.getAuthorizationHeader(),
        'Content-Type': 'multipart/form-data'
    }
};

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
                $http.post('/api/product', formProduct, config)
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error.response);
                    })
            })
        },
        getAllProducts(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product")
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
