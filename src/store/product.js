import {$http} from "../axiosConfig";

const config = {
    headers: {
        'Authorization': JSON.parse(localStorage.getItem('user')).token
    }
};

export default  {
    actions: {
        getProduct(context, productId) {
            return new Promise((resolve, reject) => {
                $http.get("/api/product " + productId)
                    .then((response) => {
                        resolve(response.data);
                    })
                    .catch(() => {
                        reject();
                    })
            })
        },
        postProduct(context, product) {
            return new Promise((resolve, reject) => {
                $http.post('/api/product', {...product}, config)
                    .then(() => {
                        console.log('done f')
                        resolve();
                    })
                    .catch((error) => {
                        console.log(error)
                        reject();
                    })
            })
        }
    },
    mutations: {
    },
    getters: {

    }
}
