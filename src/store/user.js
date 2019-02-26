

import axios from 'axios'

export default  {
    state: {
        user: localStorage.getItem('user')
    },
    actions: {
        registerUser(context, user) {
            axios.post('http://localhost:8090/api/auth/sign-up', user)
                .then(function (response) {
                    alert('success')
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    mutations: {
        setUser(state, user) {
            localStorage.setItem('user', user)
        }
    },
    getters: {
        isAuthenticated() {
            return state.user.token && state.user.authenticated;
        }
    }
}
