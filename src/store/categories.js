import {$http, getAuthConfig} from "../axiosConfig";


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
                        reject(error);
                    })
            })
        },
        getCategoryByUrl(context, categoryUrl) {
            return new Promise((resolve, reject) => {
                $http.get("/api/categories/" + categoryUrl, getAuthConfig())
                    .then(response => {
                        resolve(response.data);
                    })
                    .catch(error => {
                        reject(error);
                    })
            })
        },
        deleteCategory(context, categoryId) {
            return new Promise((resolve, reject) => {
                $http.delete("/api/categories/" + categoryId, getAuthConfig())
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        addCategory(context, category) {
            return new Promise((resolve, reject) => {
                $http.post('/api/categories', {...category}, getAuthConfig())
                    .then(() => {
                        context.dispatch('getAllCategories');
                        resolve();
                    })
                    .catch((error) => {
                        reject(error.response);
                    })
            })
        },
        updateCategory(context, category) {
            return new Promise((resolve, reject) => {
                $http.put('/api/categories', {...category}, getAuthConfig())
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
