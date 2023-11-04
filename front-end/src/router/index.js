import Vue from 'vue'
import VueRouter from 'vue-router'
import {checkAuth} from '@/utils/authService';

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('../views/Layout.vue'),
        meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '首页'},
        children: [
            {
                path: 'points',
                name: 'points',
                component: () => import('@/views/layout/PointsView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '积分排名',},
            },
            {
                path: 'charts',
                name: 'charts',
                component: () => import('@/views/layout/ChartsView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '图表展示',},
                children: [
                    {
                        path: 'stay',
                        name: 'stay',
                        component: () => import('@/views/layout/charts/ChartsStayView.vue'),
                        meta: {
                            requiresAdmin: true,
                            requiresAthlete: true,
                            requiresCoach: true,
                            breadcrumb: '留榜人数',
                        },
                    },
                    {
                        path: 'stay-rate',
                        name: 'stay-rate',
                        component: () => import('@/views/layout/charts/ChartsStayRateView.vue'),
                        meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '留榜率',},
                    },
                    {
                        path: 'entry',
                        name: 'entry',
                        component: () => import('@/views/layout/charts/ChartsEntryView.vue'),
                        meta: {
                            requiresAdmin: true,
                            requiresAthlete: true,
                            requiresCoach: true,
                            breadcrumb: '新入榜人数',
                        },
                    },
                    {
                        path: 'entry-rate',
                        name: 'entry-rate',
                        component: () => import('@/views/layout/charts/ChartsEntryRateView.vue'),
                        meta: {
                            requiresAdmin: true,
                            requiresAthlete: true,
                            requiresCoach: true,
                            breadcrumb: '新入榜率',
                        },
                    }
                ]
            },
            {
                path: 'regional-competition',
                name: 'regional-competition',
                component: () => import('@/views/layout/RegionalCompetitionView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '地区赛事',},
            },
            {
                path: 'competition-registration',
                name: 'competition-registration',
                component: () => import('@/views/layout/CompetitionRegistrationView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: false, breadcrumb: '单项比赛报名',},
            },
            {
                path: 'coaches-management',
                name: 'coaches-management',
                component: () => import('@/views/layout/CoachesManageView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '教练员列表',},
            },
            {
                path: 'athletes-management',
                name: 'athletes-management',
                component: () => import('@/views/layout/AthletesManageView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: false, requiresCoach: false, breadcrumb: '运动员管理',},
            },
            {
                path: 'information',
                name: 'information',
                component: () => import('@/views/layout/UserInformationView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '个人信息',},
            },
            {
                path: 'modify-password',
                name: 'modify-password',
                component: () => import('@/views/layout/ModifyPasswordView.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '修改密码',},
            },
            {
                path: '403',
                name: '403',
                component: () => import('@/views/layout/Error403View.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '403',},
            },
            {
                path: '404',
                name: '404',
                component: () => import('@/views/layout/Error404View.vue'),
                meta: {requiresAdmin: true, requiresAthlete: true, requiresCoach: true, breadcrumb: '404',},
            },
        ]
    },
    {path: '/login', name: '登录', component: () => import('../views/Login.vue'), meta: {breadcrumb: '登录'},},
    {path: '/register', name: '注册', component: () => import('../views/Register.vue'), meta: {breadcrumb: '注册'},},
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

    const publicPaths = ['/', '/login', '/register'];

    const authFlag = checkAuth(token);

    // 检查用户是否已经登录
    if (!authFlag) {
        // 未登录跳转至公共登录页面
        if (!publicPaths.includes(to.path)) {
            return next('/login');
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

    // 处理未匹配的路由
    if (to.matched.length === 0) {
        return next('/404');
    }

    if (requiredPermission && !to.matched.some(record => record.meta[requiredPermission])) {
        // 用户没有足够的权限访问目标页面 重定向到其他页面或添加错误消息
        return next('/403');
    }

    // 对于已登录的用户访问公共路径，重定向到'/points'
    if (authFlag && publicPaths.includes(to.path)) {
        return next('/points');
    }

    // 如果已登录的用户访问'/charts'，重定向到'/charts/stay'
    if (authFlag && to.path === '/charts') {
        return next('/charts/stay');
    }

    // 用户已登录且具有足够的权限
    next();
});


// 解决vue-router在3.0版本以上重复报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default router
