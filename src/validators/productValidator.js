import {$http} from "../axiosConfig";
import JwtHelper from "../helpers/jwtHelper";

function getConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}

export function checkProduct(name, color) {
    return new Promise((resolve, reject) => {
        $http.post('/api/product/exist', {name, color}, getConfig())
            .then((response) => {
                resolve(!response.data.isExist);
            })
            .catch((error) => {
                reject();
            })
    })
}
