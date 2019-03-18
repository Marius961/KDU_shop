import {$http} from "../axiosConfig";
import JwtHelper from "../helpers/jwtHelper";


function getConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}


export function isNameUnique(value) {
    return new Promise((resolve, reject) => {
        $http.post("/api/categories/check-name", {categoryName: value}, getConfig())
            .then((response) => {
                resolve(!response.data.isExist)
            })
            .catch(() => {
                reject()
            })
    })
}

export function isUrlUnique(value) {
    return new Promise((resolve, reject) => {
        $http.post("/api/categories/check-url", {categoryUrl: value}, getConfig())
            .then((response) => {
                resolve(!response.data.isExist)
            })
            .catch(() => {
                reject()
            })
    })
}
