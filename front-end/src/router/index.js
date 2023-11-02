import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '首页',
        component: () => import('../views/HomeView.vue'),
        redirect: '/points',
        children: [
            {path: 'points', name: '积分排名', component: () => import('../views/PointsView.vue'),},
            {path: 'charts', name: '图表展示', component: () => import('../views/ChartsView.vue'),},
            {path: 'charts/stay', name: '留榜人数', component: () => import('../views/ChartsStayView.vue'),},
            {path: 'charts/stay-rate', name: '留榜率', component: () => import('../views/ChartsStayRateView.vue'),},
            {path: 'charts/entry', name: '新入榜人数', component: () => import('../views/ChartsEntryView.vue'),},
            {path: 'charts/entry-rate', name: '新入榜率', component: () => import('../views/ChartsEntryRateView.vue'),},
            {path: 'manage', name: '信息管理', component: () => import('../views/ManageView.vue'),},

        ]
    },
    {
        path: '/login',
        name: '注册',
        component: () => import('../views/Login.vue'),
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
