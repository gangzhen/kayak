import axios from 'axios'

const request = axios.create({
    baseURL: process.env.VUE_APP_BASEURL,
    timeout: 50000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    const user = JSON.parse(localStorage.getItem("userInfo") || '{}');
    config.headers['token'] = user.token;  // 设置请求头

    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {

        // 获取新的JWT token
        const newToken = response.headers['x-new-token'];
        const user = JSON.parse(localStorage.getItem("userInfo") || "{}");

        if (user.token && newToken) {
            user.token = newToken;
            // 更新本地存储的JWT token
            localStorage.setItem('userInfo', JSON.stringify(user));
            localStorage.setItem('token', newToken);
        }

        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request
