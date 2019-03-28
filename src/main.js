import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Vuelidate from 'vuelidate'
import vbclass from 'vue-body-class'
Vue.config.productionTip = false;


Vue.use(Vuelidate);
Vue.use( vbclass, router )




new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
});
