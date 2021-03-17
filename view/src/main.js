import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'materialize-css/dist/css/materialize.min.css'
import 'material-design-icons/iconfont/material-icons.css'
import VueLettering from '@miii/vue-lettering'

Vue.use(VueLettering)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)

}).$mount('#app')
