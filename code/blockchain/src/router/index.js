import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import File from '@/components/File'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome
    },  {
      path: '/',
      name: 'File',
      component: File
    },
  ]
})
