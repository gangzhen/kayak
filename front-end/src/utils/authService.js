import {jwtDecode} from 'jwt-decode';

export function checkAuth(token) {
    if (token) {
        // 解码JWT令牌以获取令牌信息
        const decodedToken = jwtDecode(token);
        const currentTime = Date.now() / 1000;

        if (decodedToken.exp > currentTime) {
            // 令牌有效，用户已登录
            return true;
        }
    }
    // 令牌无效或已过期，用户未登录
    return false;
}