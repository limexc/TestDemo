import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import echarts from 'echarts'
import './style/Default.css'


axios.defaults.baseURL = 'http://localhost:8081/'
axios.defaults.headers.post['Content-Type'] = 'application/json';
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false



new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
