import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '首页',
    component:  () => import('../views/HomeView.vue'),
    redirect: '/points',
    children: [
      { path: 'points', name: '积分排名', component: () => import('../views/PointsView.vue'), },
      { path: 'charts', name: '图表展示', component: () => import('../views/ChartsView.vue'), },
      { path: 'manage', name: '信息管理', component: () => import('../views/ManageView.vue'), },

    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
