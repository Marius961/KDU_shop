import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Products from '../pages/products'
import Cart from '../pages/cart'
import Product from '../pages/product'
import Registration from '../pages/registration'
import Login from '../pages/login'
import AddCategory from '../pages/adminPanel/categoriesPanel/addCategory'
import Error404 from "../pages/Error404";
import CreateOrder from '../pages/createOrder'
import myOrders from "../pages/myOrders";
import orderManagement from "../pages/orderManagement";
import adminPanel from "../pages/adminPanel/adminPanel";
import categoriesPanel from "../pages/adminPanel/categoriesPanel/categoriesPanel";
import categoriesList from "../pages/adminPanel/categoriesPanel/categoriesList";
import UpdateCategory from "../pages/adminPanel/categoriesPanel/UpdateCategory";
import productsPanel from "../pages/adminPanel/productsPanel/productsPanel";
import addProduct from "../pages/adminPanel/productsPanel/addProduct";
import ProductsList from "../pages/adminPanel/productsPanel/ProductsList";
import updateProduct from "../pages/adminPanel/productsPanel/updateProduct";

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
      component: Product,
      meta: {
        bodyClass: 'product-page'
      }
    },
    {
      path: '/registration',
      component: Registration,
      meta: {
        requiresRoles: [],
        bodyClass: 'auth-form-page'
      }
    },
    {
      path: '/login',
      component: Login,
      meta: {
        requiresRoles: [],
        bodyClass: 'auth-form-page'
      }
    },
    {
      path: '/create-order',
      component: CreateOrder,
      meta: {
        requiresAuth: true,
        requiresRoles: ["USER"],
        bodyClass: 'product-page'
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
    {
      path: '/admin-panel',
      component: adminPanel,
      redirect: '/admin-panel/categories',
      meta: {
        requiresAuth: true,
        requiresRoles: ["ADMIN"],
        bodyClass: 'adding-page-form'
      },
      children: [
        {
          path: 'categories',
          component: categoriesPanel,
          meta: {
            requiresAuth: true,
            requiresRoles: ["ADMIN"]
          },
          redirect: '/admin-panel/categories/add',
          children: [
            {
              path: 'add',
              component: AddCategory,
              meta: {
                requiresAuth: true,
                requiresRoles: ["ADMIN"]
              }
            },
            {
              path: 'list',
              component: categoriesList,
              meta: {
                requiresAuth: true,
                requiresRoles: ["ADMIN"]
              }
            },
            {
              path: 'update/:categoryUrl',
              component: UpdateCategory,
              props: true,
              meta: {
                requiresAuth: true,
                requiresRoles: ["ADMIN"],
              }
            },
          ]
        },
        {
          path: 'products',
          component: productsPanel,
          meta: {
            requiresAuth: true,
            requiresRoles: ["ADMIN"]
          },
          redirect: '/admin-panel/products/add',
          children: [
            {
              path: 'add',
              component: addProduct,
              meta: {
                requiresAuth: true,
                requiresRoles: ["ADMIN"],
              }
            },
            {
              path: 'list',
              component: ProductsList,
              meta: {
                requiresAuth: true,
                requiresRoles: ["ADMIN"],
              }
            },
            {
              path: 'update/:productId',
              component: updateProduct,
              props: true,
              meta: {
                requiresAuth: true,
                requiresRoles: ["ADMIN"],
              }
            },
          ]
        }
      ]
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
