import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n
import '@/styles/index.scss' // global css
import App from './App'
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission'
import axios from 'axios' // permission control

axios.defaults.baseURL = 'http://localhost:8081/'
axios.defaults.headers.post['Content-Type'] = 'application/json'

Vue.use(ElementUI, { locale })

Vue.config.productionTip = false
Vue.use(ElementUI, { locale })
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
