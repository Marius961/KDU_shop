import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Axios from 'axios'
Vue.config.productionTip = false;

Vue.prototype.$http = Axios;

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
});
