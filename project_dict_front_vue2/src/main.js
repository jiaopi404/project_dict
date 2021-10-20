import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/util/exception' // 处理异常
import '@/filters' // 注册全局 filters
// import '@/util/testError'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/styles/common.scss'
import '@/styles/cus-common.scss'

Vue.use(ElementUI)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
