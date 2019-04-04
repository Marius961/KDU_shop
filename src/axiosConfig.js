import axios from 'axios'
import JwtHelper from "./helpers/jwtHelper";

export const $http = axios.create({
    baseURL: 'http://localhost:8090',
})

export function getAuthConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}
