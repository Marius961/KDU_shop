import {$http} from "../axiosConfig";
import JwtHelper from "../helpers/jwtHelper";

function getConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
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
        }
    },
    mutations: {

    },
    getters: {

    }
}
