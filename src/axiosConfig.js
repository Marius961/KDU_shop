import axios from 'axios'
import JwtHelper from "./helpers/jwtHelper";
import router from './router'


export const $http = axios.create({
    baseURL: 'http://localhost:8090',

});

$http.interceptors.response.use(function (response) {
    // Do something with response data
    return response;
}, function (error) {
    // Do something with response error
    if (403 === error.response.status) {
        const user = JwtHelper.getLocalUser();

        if (JwtHelper.isTokenNotExpired(user.token)) {
            router.push("/logout")
        }
    }
    return Promise.reject(error);
});





export default $http;

export function getAuthConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}
