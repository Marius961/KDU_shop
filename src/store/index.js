import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

import Cart from './cart'
import Auth from './auth'
import Categories from './categories'
import Product from './product'
import Order from './order'
import Users from './users'

export default new Vuex.Store({
  modules: {
    Cart,
    Auth,
    Categories,
    Product,
    Order,
    Users
  }
})
