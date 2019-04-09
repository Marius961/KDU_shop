import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Vuelidate from 'vuelidate'
import vbclass from 'vue-body-class'
Vue.config.productionTip = false;
import VueSweetalert2 from 'vue-sweetalert2';



Vue.use(
    vbclass,
    router,
    Vuelidate,
    VueSweetalert2
);


new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
});
