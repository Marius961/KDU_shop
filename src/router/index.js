import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Products from '../pages/products'
import Cart from '../pages/cart'
import Product from '../pages/product'
import Registration from '../pages/registration'
import Login from '../pages/login'


Vue.use(Router);

const router = new Router({
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
      component: Cart,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/product/:id',
      component: Product
    },
    {
      path: '/registration',
      component: Registration,
      meta: {
        requiresRoles: [""]
      }
    },
    {
      path: '/login',
      component: Login
    }
  ]
});


router.beforeEach((to, from, next) => {
  const user = JSON.parse(localStorage.getItem('user'));
  if (to.meta.requiresAuth) {

    if (user && user.token) {
      next()
    } else next('/login')
  }
  else if(to.meta.requiresRoles) {
    let roles = [""];
    if (user && user.roles) {
      roles = user.roles;
    }

    const found = to.meta.requiresRoles.some(r => roles.indexOf(r) >= 0);
    if (found) {
      next();
    } else {
      next('/404')
    }
  }
  next();
});

export default router;
