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
        }
    },
    mutations: {

    },
    getters: {

    }
}
