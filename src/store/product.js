import {$http} from "../axiosConfig";

export default  {
    actions: {
        getProduct(context, productId) {
            return new Promise((resolve, reject) => {
                $http.get()
                //TODO: complete this
            })
        }
    },
    mutations: {
    },
    getters: {

    }
}
