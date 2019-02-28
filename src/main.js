import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Vuelidate from 'vuelidate'
Vue.config.productionTip = false;


Vue.use(Vuelidate);




new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
});
