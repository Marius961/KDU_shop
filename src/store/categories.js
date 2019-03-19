import {$http} from "../axiosConfig";
import JwtHelper from '../helpers/jwtHelper'

function getConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}


export default  {
    state: {
        categories: []
    },
    actions: {
        getAllCategories(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/categories")
                    .then(response => {
                        context.commit('setCategories', response.data);
                        resolve();
                    })
                    .catch(error => {
                        reject();
                    })
            })
        },
        addCategory(context, category) {
            return new Promise((resolve, reject) => {
                $http.post('/api/categories', {...category}, getConfig())
                    .then(() => {
                        context.dispatch('getAllCategories');
                        resolve();
                    })
                    .catch((error) => {
                        reject(error.response);
                    })
            })
        }
    },
    mutations: {
        setCategories(state, categories) {
            state.categories = categories;
        }
    },
    getters: {
        getCategories(state) {
            return state.categories;
        }
    }
}
