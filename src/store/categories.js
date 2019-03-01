import {$http} from "../axiosConfig";


const config = {
    headers: {
        'Authorization': JSON.parse(localStorage.getItem('user')).token
    }
};


export default  {
    state: {
        categories: []
    },
    actions: {
        getAllCategories(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/categories")
                    .then(response => {
                        console.log(response);
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
                $http.post('/api/categories', {...category}, config)
                    .then(() => {
                        context.dispatch('getAllCategories');
                        resolve();
                    })
                    .catch(() => {
                        reject();
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
