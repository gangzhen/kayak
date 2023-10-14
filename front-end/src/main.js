import Vue from 'vue'
import App from './App.vue'
import router from './router'

import ElementUI from 'element-ui';
import echarts from 'echarts'
import request from "@/utils/request";

import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/global.css'
import '@/assets/css/theme/index.css'

Vue.config.productionTip = false
Vue.use(ElementUI, {size: 'small'});
Vue.prototype.$echarts = echarts
Vue.prototype.$http = request
Vue.prototype.$baseUrl = process.env.VUE_APP_BASEURL

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
