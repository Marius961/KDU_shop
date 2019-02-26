import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Products from '../pages/products'
import Cart from '../pages/cart'
import Product from '../pages/product'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/products',
      component: Products,
    },
    {
      path: '/cart',
      component: Cart
    },
    {
      path: '/product/:id',
      component: Product
    }
  ]
})
