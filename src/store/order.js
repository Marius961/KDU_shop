import {$http} from "../axiosConfig";
import JwtHelper from "../helpers/jwtHelper";

function getConfig(params) {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        },
        params
    }
}

export default  {
    state: {

    },
    actions: {
        postOrder(context, order) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders", order, getConfig())
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getOrders(context, query) {
            return new Promise((resolve, reject) => {
                $http.get("/api/orders", getConfig(query))
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        getAllOrders(context, query) {
            return new Promise((resolve, reject) => {
                $http.get("/api/orders/all", getConfig(query))
                    .then((response) => {
                        console.log(response.data)
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        cancelOrder(context, orderId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders/cancel/" + orderId, null,  getConfig({}))
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        martAsShippedOut(context, orderId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders/shipped-out/" + orderId, null, getConfig({}))
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        confirmOrder(context, orderId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders/confirm/" + orderId, null, getConfig({}))
                    .then(() => {
                        resolve();
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
