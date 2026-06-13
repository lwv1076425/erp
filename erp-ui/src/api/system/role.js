import request from '@/utils/request'

// 角色列表
export function listRole(params) {
  return request({
    url: '/system/role',
    method: 'get',
    params
  })
}

// 角色详情
export function getRole(id) {
  return request({
    url: '/system/role/' + id,
    method: 'get'
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/system/role',
    method: 'post',
    data
  })
}

// 修改角色
export function updateRole(id, data) {
  return request({
    url: '/system/role/' + id,
    method: 'put',
    data
  })
}

// 删除角色
export function deleteRole(id) {
  return request({
    url: '/system/role/' + id,
    method: 'delete'
  })
}
