import {$http} from "../axiosConfig";

export function isUserRegistered(value) {
    return new Promise((resolve, reject) => {
        $http.post("/api/auth/is-registered", {username: value})
            .then((response) => {
                resolve(!response.data.isRegistered)
            })
            .catch(() => {
                reject()
            })
    })
}

export function isEmailRegistered(value) {
    return new Promise((resolve, reject) => {
        $http.post("/api/auth/is-email-exist", {email: value})
            .then((response) => {
                resolve(!response.data.isExist)
            })
            .catch(() => {
                reject()
            })
    })
}
