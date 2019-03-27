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
                    .catch(() => {
                        reject();
                    })
            })
        },
        getOrders(context, query) {
            return new Promise((resolve, reject) => {
                $http.get("/api/orders", getConfig(query))
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        getAllOrders(context, query) {
            return new Promise((resolve, reject) => {
                $http.get("/api/orders/all", getConfig(query))
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        cancelOrder(context, orderId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders/cancel/" + orderId, null,  getConfig({}))
                    .then(() => {
                        resolve();
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        martAsShippedOut(context, orderId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders/shipped-out/" + orderId, null, getConfig({}))
                    .then(() => {
                        resolve();
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        confirmOrder(context, orderId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/orders/confirm/" + orderId, null, getConfig({}))
                    .then(() => {
                        resolve();
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
