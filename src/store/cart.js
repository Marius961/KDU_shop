import {$http} from "../axiosConfig";
import JwtHelper from "../helpers/jwtHelper";

function getConfig() {
    return {
        headers: {
            ...JwtHelper.getAuthorizationHeader()
        }
    }
}

export default  {
    state: {
        cartItems: []
    },
    actions: {
        getCartItems(context) {
            return new Promise((resolve, reject) => {
                $http.get("/api/cart", getConfig())
                    .then((response) => {
                        resolve();
                        context.commit("LOAD_SUCCESS", response.data)
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        postCartItem(context, cartItem) {
            return new Promise((resolve, reject) => {
                $http.post("/api/cart",cartItem, getConfig())
                    .then(() => {
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        deleteCartItem(context, itemId) {
            return new Promise((resolve, reject) => {
                $http.delete("/api/cart/" + itemId, getConfig())
                    .then(() => {
                        context.commit("deleteItemFromStore", itemId);
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        },
        changeItemQuantity(context, itemData) {
            return new Promise((resolve, reject) => {
                $http.put("/api/cart/" + itemData.itemId + "/" + itemData.quantity,null, getConfig())
                    .then(() => {
                        context.commit("changeQuantity", itemData);
                        resolve();
                    })
                    .catch((error) => {
                        reject(error);
                    })
            })
        }
    },
    mutations: {
        LOAD_SUCCESS(state, cartItems) {
            state.cartItems = cartItems;
        },
        addItemToCart(state, item) {
            state.cartItems.push(item);
        },
        deleteItemFromStore(state, itemId) {
            const index =  state.cartItems.findIndex(obj => obj.id === itemId);
            state.cartItems.splice(index, 1);
        },
        changeQuantity(state, cartItemData) {
            const index =  state.cartItems.findIndex(obj => obj.id === cartItemData.itemId);
            state.cartItems[index].quantity = cartItemData.quantity;

        }
    },
    getters: {
        getCartItemsList(state) {
            return state.cartItems;
        },
        totalCartPrice(state) {
            let totalPrice = 0;
            state.cartItems.map(item => {
                totalPrice += item.quantity * item.product.price
            });
            return totalPrice;
        }
    }
}
