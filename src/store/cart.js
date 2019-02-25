




export default  {
  state: {
    cartItems: []
  },
  actions: {
  },
  mutations: {
    loadCart(state) {
      state.cartItems = [];
    },
    addItemToCart(state, item) {
      console.log(item);
      state.cartItems.push(item);
    }
  },
  getters: {
    getCartItems(state) {
      return state.cartItems;
    }
  }
}
