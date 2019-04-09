import Vue from 'vue'
import Router from 'vue-router'
import Home from '../pages/Home'
import Products from '../pages/Products'
import Cart from '../pages/Cart'
import Product from '../pages/Product'
import Registration from '../pages/Registration'
import Login from '../pages/Login'
import AddCategory from '../pages/adminPanel/categoriesPanel/AddCategory'
import Error404 from "../pages/Error404";
import CreateOrder from '../pages/CreateOrder'
import myOrders from "../pages/MyOrdersList";
import orderManagement from "../pages/adminPanel/OrdersPanel";
import adminPanel from "../pages/adminPanel/AdminPanel";
import categoriesPanel from "../pages/adminPanel/categoriesPanel/CategoriesPanel";
import categoriesList from "../pages/adminPanel/categoriesPanel/CategoriesList";
import UpdateCategory from "../pages/adminPanel/categoriesPanel/UpdateCategory";
import productsPanel from "../pages/adminPanel/productsPanel/ProductsPanel";
import addProduct from "../pages/adminPanel/productsPanel/AddProduct";
import ProductsList from "../pages/adminPanel/productsPanel/ProductsList";
import updateProduct from "../pages/adminPanel/productsPanel/UpdateProduct";
import UsersManagement from "../pages/adminPanel/UsersPanel";
import Account from "../pages/Account";

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
            path: '*',
            redirect: '/errors/404'
        },
        {
            path: '/errors/404',
            component: Error404,
            meta: {
                bodyClass: 'body-dark'
            }
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
            component: Product,
            meta: {
                bodyClass: 'body-light'
            }
        },
        {
            path: '/registration',
            component: Registration,
            meta: {
                requiresRoles: [],
                bodyClass: 'body-dark'
            }
        },
        {
            path: '/login',
            component: Login,
            meta: {
                requiresRoles: [],
                bodyClass: 'body-dark'
            }
        },
        {
            path: '/create-order',
            component: CreateOrder,
            meta: {
                requiresAuth: true,
                bodyClass: 'body-light'
            }
        },
        {
            path: '/orders',
            component: myOrders,
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/account',
            component: Account,
            meta: {
                requiresAuth: true,
                bodyClass: 'body-dark'
            }
        },
        {
            path: '/admin-panel',
            component: adminPanel,
            redirect: '/admin-panel/categories',
            meta: {
                requiresRoles: ["ADMIN"],
                bodyClass: 'body-dark'
            },
            children: [
                {
                    path: '/orders-manage',
                    component: orderManagement,
                    meta: {
                        requiresRoles: ["ADMIN"]
                    }
                },
                {
                    path: '/users-manage',
                    component: UsersManagement,
                    meta: {
                        requiresRoles: ["ADMIN"]
                    }
                },
                {
                    path: 'categories',
                    component: categoriesPanel,
                    meta: {
                        requiresRoles: ["ADMIN"]
                    },
                    redirect: '/admin-panel/categories/add',
                    children: [
                        {
                            path: 'add',
                            component: AddCategory,
                            meta: {
                                requiresRoles: ["ADMIN"]
                            }
                        },
                        {
                            path: 'list',
                            component: categoriesList,
                            meta: {
                                requiresRoles: ["ADMIN"]
                            }
                        },
                        {
                            path: 'update/:categoryUrl',
                            component: UpdateCategory,
                            props: true,
                            meta: {
                                requiresRoles: ["ADMIN"]
                            }
                        }
                    ]
                },
                {
                    path: 'products',
                    component: productsPanel,
                    meta: {
                        requiresRoles: ["ADMIN"]
                    },
                    redirect: '/admin-panel/products/add',
                    children: [
                        {
                            path: 'add',
                            component: addProduct,
                            meta: {
                                requiresRoles: ["ADMIN"]
                            }
                        },
                        {
                            path: 'list',
                            component: ProductsList,
                            meta: {
                                requiresRoles: ["ADMIN"]
                            }
                        },
                        {
                            path: 'update/:productId',
                            component: updateProduct,
                            props: true,
                            meta: {
                                requiresAuth: true,
                                requiresRoles: ["ADMIN"]
                            }
                        }
                    ]
                }
            ]
        }
    ],
    scrollBehavior () {
        return { x: 0, y: 0 }
    },
    mode: 'history'
});


router.beforeEach((to, from, next) => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (to.meta.requiresAuth && !to.meta.requiresRoles) {

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
                next();
            } else {
                next('/errors/404')
            }
        }
    }
    next();
});

export default router;
