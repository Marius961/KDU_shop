import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

import Cart from './cart'
import Products from './products'
import User from './user'


export default new Vuex.Store({
  modules: {
    Cart,
    Products,
    User
  }
})
