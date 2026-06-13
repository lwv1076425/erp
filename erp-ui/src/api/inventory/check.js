import request from '@/utils/request'

export function listInvCheck(params) {
  return request({ url: '/inventory/check', method: 'get', params })
}
export function addInvCheck(data) {
  return request({ url: '/inventory/check', method: 'post', data })
}
export function deleteInvCheck(id) {
  return request({ url: '/inventory/check/' + id, method: 'delete' })
}
