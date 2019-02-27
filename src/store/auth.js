import {$http} from "../axiosConfig";
import  jwtHelper from '../helpers/jwtHelper'

export default  {
    state: {
        user: JSON.parse(localStorage.getItem('user'))
    },
    actions: {
        registerUser(context, user) {
            return $http.post('/api/auth/sign-up', user)
                .then((response) => {
                    context.dispatch('loginUser', {
                        username: user.username,
                        password: user.password
                    }).then((isSuccess) => {
                        return !!isSuccess;
                    })
                })
                .catch((error) => {
                    return false;
                });
        },
        loginUser(context, credentials) {
            return $http.post('/api/auth/sign-in', {...credentials})
                .then((response) => {
                    const token = response.headers['authorization'];
                    let roles = jwtHelper.decodeClaim('roles', token);
                    if (roles) {
                        roles = roles.split(',');
                        context.commit('LOGIN_SUCCESS', {
                            username: credentials.username,
                            token,
                            roles
                        });
                        return true;
                    }
                    return false;
                })
                .catch(function (error) {
                    console.log(error);
                    return false;
                });
        }
    },
    mutations: {
        LOGIN_SUCCESS(state, user) {
            state.user = user;
            console.log(state.user.username);
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
            return state.user.username
        }
    }
}
