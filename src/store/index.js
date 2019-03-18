import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

import Cart from './cart'
import User from './auth'
import Categories from './categories'
import Product from './product'

export default new Vuex.Store({
  modules: {
    Cart,
    User,
    Categories,
    Product
  }
})
