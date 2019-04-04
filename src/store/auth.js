import {$http} from "../axiosConfig";
import  jwtHelper from '../helpers/jwtHelper'



export default  {
    state: {
        user: JSON.parse(localStorage.getItem('user'))
    },
    actions: {
        registerUser(context, user) {
            return new Promise((resolve, reject) => {
                $http.post('/api/auth/sign-up', user)
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    });
                }).then(() => {
                    context.dispatch('loginUser', {username: user.username, password: user.password})
                })
        },
        loginUser(context, credentials) {
            return new Promise((resolve, reject) => {
                $http.post('/api/auth/sign-in', {...credentials})
                    .then((response) => {
                        const token = response.headers['authorization'];
                        let roles = jwtHelper.decodeClaim('roles', token);
                        if (roles && token) {
                            context.commit('LOGIN_SUCCESS', { username: credentials.username, token, roles });
                            resolve();
                        }
                        reject();
                    })
                    .catch( (error) => {
                        reject(error);
                     });
            })
        }
    },
    mutations: {
        LOGIN_SUCCESS(state, user) {
            user.roles = user.roles.split(',');
            state.user = user;
            localStorage.setItem('user', JSON.stringify(user));
        },
        LOGOUT(state) {
            state.user = null;
            localStorage.clear();
        }
    },
    getters: {
        isAuthenticated(state) {
            return state.user !== null;
        },
        getUsername(state) {
            if (state.user) {
                return state.user.username;
            } else return null;
        },
        getToken(state) {
            return state.user.token
        },
        isAdmin(state) {
            if (state.user && state.user.roles) {
                return state.user.roles.indexOf("ADMIN") >= 0
            } else return false;
        }
    }
}
