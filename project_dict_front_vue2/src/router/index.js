import Vue from 'vue'
import VueRouter from 'vue-router'
import HeaderOnly from '@/layout/HeaderOnly'
import PageRecite from '@/views/PageRecite'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HeaderOnly,
    children: [
      {
        path: '/',
        name: 'Recite',
        component: PageRecite
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
