export default  {
  state: {
    products: [
      {
        id: 0,
        name: 'Футболка УКД',
        color: 'Чорний',
        sizes: [
          {
            id: 0,
            name: 'L'
          },
          {
            id: 1,
            name: "s"
          }
        ],
        image: 'http://pngimg.com/uploads/tshirt/tshirt_PNG5450.png',
        price: 22,
        description: 'Test',
        category: {
          id: 0,
          name: 'tShirts'
        }
      }
    ]
  },
  actions: {
  },
  mutations: {
  },
  getters: {
    getProducts(state) {
      return state.products;
    },
    getProduct: (state) => (id) => {
      return state.products[state.products.findIndex(obj => obj.id === id)];
    }
  }
}
