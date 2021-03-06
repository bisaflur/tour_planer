import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Request.vue')
  },
  {
    path: '/plan/:city/:range',
    name: 'Plan',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Plan.vue')
  },
  {
    path: '/result/:city/:resultList',
    name: 'Result',
    component: () => import('../views/Result.vue')
  }
]
const router = new VueRouter({
  routes
})

export default router
