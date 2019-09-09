// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import * as Axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/base.css';
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';

Vue.component(CollapseTransition.name, CollapseTransition);
Vue.use(ElementUI);
Vue.config.productionTip = false;

// Axios.interceptors.response.use((response)=>{
//   // 处理响应数据
//   return response;
// },  (error)=>{
//   console.log(JSON.stringify(error));
//   console.log(error.response);
//   // 处理响应失败
//   console.log('err:'+error);
//   return Promise.reject(error);
// });

Vue.prototype.$axios = Axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
