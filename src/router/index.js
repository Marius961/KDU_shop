import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Products from '../pages/products'
import Cart from '../pages/cart'
import Product from '../pages/product'
import Registration from '../pages/registration'
import Login from '../pages/login'
import AddCategory from '../pages/addCategory'
import AddProduct from '../pages/addProduct'
import Error404 from "../pages/Error404";
import CreateOrder from '../pages/createOrder'
import myOrders from "../pages/myOrders";
import orderManagement from "../pages/orderManagement";

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/products/:categoryUrl',
      component: Products,
      props: true
    },
    {
      path: '/errors/404',
      component: Error404,
    },
    {
      path: '/cart',
      component: Cart,
      meta: {
        requiresAuth: true,
        requiresRoles: ["USER"]
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
        requiresRoles: [],
        bodyClass: 'login-form-page'
      }
    },
    {
      path: '/login',
      component: Login,
      meta: {
        requiresRoles: [],
        bodyClass: 'login-form-page'
      }
    },
    {
      path: '/add-category',
      component: AddCategory,
      meta: {
        requiresAuth: true,
        requiresRoles: ["ADMIN"]
      }
    },
    {
      path: '/add-product',
      component: AddProduct,
      meta: {
        requiresAuth: true,
        requiresRoles: ["ADMIN"]
      }
    },
    {
      path: '/create-order',
      component: CreateOrder,
      meta: {
        requiresAuth: true,
        requiresRoles: ["USER"]
      }
    },
    {
      path: '/orders',
      component: myOrders,
      meta: {
        requiresAuth: true,
        requiresRoles: ["USER"]
      }
    },
    {
      path: '/orders/manage',
      component: orderManagement,
      meta: {
        requiresAuth: true,
        requiresRoles: ["ADMIN"]
      }
    },
  ]
});


router.beforeEach((to, from, next) => {
  const user = JSON.parse(localStorage.getItem('user'));
  if (to.meta.requiresAuth) {

    if (user && user.token) {

      next()
    } else next('/login')
  }
  else if (to.meta.requiresRoles) {
    if (to.meta.requiresRoles.length === 0) {
      next();
    } else {
      let roles = [];
      if (user && user.roles) {
        roles = user.roles;
      }

      const found = to.meta.requiresRoles.some(r => roles.indexOf(r) >= 0);
      if (found) {
        alert('ok');
        next();
      } else {
        next('/errors/404')
      }
    }
  }
  next();
});

export default router;
