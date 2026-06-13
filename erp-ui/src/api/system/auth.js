import request from '@/utils/request'

// 登录
export function login(data) {
  return request({
    url: '/system/auth/login',
    method: 'post',
    data
  })
}

// 获取用户信息
export function getInfo() {
  return request({
    url: '/system/auth/info',
    method: 'get'
  })
}

// 退出登录
export function logout() {
  return request({
    url: '/system/auth/logout',
    method: 'post'
  })
}
