import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import File from '@/components/File'

Vue.use(Router);

export default new Router({
  mode:'hash',
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: Welcome
    },  {
      path: '/file',
      name: 'file',
      component: File
    },{
      path:'*',
      redirect:'/'},
  ]
})
