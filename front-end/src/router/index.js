import Vue from 'vue'
import VueRouter from 'vue-router'
import {checkAuth} from '@/utils/authService';

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '首页',
        component: () => import('../views/Layout.vue'),
        children: [
            {
                path: 'points',
                name: '积分排名',
                component: () => import('@/views/layout/PointsView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'charts',
                name: '图表展示',
                component: () => import('@/views/layout/ChartsView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'charts/stay',
                name: '留榜人数',
                component: () => import('@/views/layout/ChartsStayView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'charts/stay-rate',
                name: '留榜率',
                component: () => import('@/views/layout/ChartsStayRateView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'charts/entry',
                name: '新入榜人数',
                component: () => import('@/views/layout/ChartsEntryView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'charts/entry-rate',
                name: '新入榜率',
                component: () => import('@/views/layout/ChartsEntryRateView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'regional-competition',
                name: '地区赛事',
                component: () => import('@/views/layout/RegionalCompetitionView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'competition-registration',
                name: '单项比赛报名',
                component: () => import('@/views/layout/CompetitionRegistrationView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: false},
            },
            {
                path: 'coaches-management',
                name: '教练员列表',
                component: () => import('@/views/layout/CoachesManageView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: 'athletes-management',
                name: '运动员管理',
                component: () => import('@/views/layout/AthletesManageView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: false, requiresCoach: false},
            },
            {
                path: '403',
                name: '403',
                component: () => import('@/views/layout/Error403View.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
            {
                path: '404',
                name: '404',
                component: () => import('@/views/layout/Error404View.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true},
            },
        ]
    },
    {path: '/login', name: '登录', component: () => import('../views/Login.vue'),},
    {path: '/register', name: '注册', component: () => import('../views/Register.vue'),},
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {

    let user = JSON.parse(localStorage.getItem("userInfo") || "{}");
    const token = user.token; // 从本地存储中获取JWT令牌
    const userRole = user.role; // 从本地存储中获取用户角色

    // 检查用户是否已登录
    if (!checkAuth(token)) {
        // 用户未登录
        if (to.path !== '/login' && to.path !== '/register') {
            return next('/login');
        } else {
            return next();
        }
    }

    // 定义角色权限映射
    const rolePermissions = {
        'admin': 'requiresAdmin',
        'athlete': 'requiresAthlete',
        'coach': 'requiresCoach',
        // 添加其他角色和对应的权限
    };

    const requiredPermission = rolePermissions[userRole];

    if (to.matched.length === 0) {
        // 处理未找到的页面
        return next('/404');

    }
    if (requiredPermission && !to.matched.some(record => record.meta[requiredPermission])) {
        // 用户没有足够的权限访问目标页面
        return next('/403'); // 可以重定向到其他页面或添加错误提示
    }

    // 用户已登录且具有足够的权限
    next();
});

export default router
