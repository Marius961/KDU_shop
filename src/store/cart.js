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
            .catch(() => {
              reject();
            })
      })
    },
    postCartItem(context, cartItem) {
      return new Promise((resolve, reject) => {
        $http.post("/api/cart",cartItem, getConfig())
            .then(() => {
              resolve();
            })
            .catch(() => {
              reject();
            })
      })
    },
  },
  mutations: {
    LOAD_SUCCESS(state, cartItems) {
      state.cartItems = cartItems;
    },
    addItemToCart(state, item) {
      state.cartItems.push(item);
    }
  },
  getters: {
    getCartItemsList(state) {
      return state.cartItems;
    }
  }
}
