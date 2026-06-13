import request from '@/utils/request'

// 用户列表
export function listUser(params) {
  return request({
    url: '/system/user',
    method: 'get',
    params
  })
}

// 用户详情
export function getUser(id) {
  return request({
    url: '/system/user/' + id,
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data
  })
}

// 修改用户
export function updateUser(id, data) {
  return request({
    url: '/system/user/' + id,
    method: 'put',
    data
  })
}

// 删除用户
export function deleteUser(id) {
  return request({
    url: '/system/user/' + id,
    method: 'delete'
  })
}
