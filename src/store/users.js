import {$http, getAuthConfig} from "../axiosConfig";

export default  {
    actions: {
        getAllUsers(context, query) {
            return new Promise((resolve, reject) => {
                $http.get("/api/user/", {params: query, ...getAuthConfig()})
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        setAdmin(context, userId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/user/set-admin/" + userId, null, getAuthConfig())
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        removeAdmin(context, userId) {
            return new Promise((resolve, reject) => {
                $http.post("/api/user/remove-admin/" + userId,null, getAuthConfig())
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
