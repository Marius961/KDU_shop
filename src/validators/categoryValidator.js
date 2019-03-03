import {$http} from "../axiosConfig";

export function isNameUnique(value) {
    return new Promise((resolve, reject) => {
        $http.post("/api/categories/check-name", {categoryName: value})
            .then((response) => {
                resolve(!response.data.isExist)
            })
            .catch(() => {
                alert("error")
                reject()
            })
    })
}

export function isUrlUnique(value) {
    return new Promise((resolve, reject) => {
        $http.post("/api/categories/check-url", {categoryUrl: value})
            .then((response) => {
                resolve(!response.data.isExist)
            })
            .catch(() => {
                reject()
            })
    })
}
