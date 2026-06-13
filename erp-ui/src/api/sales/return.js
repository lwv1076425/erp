import request from '@/utils/request'

export function listSalReturn(params) {
  return request({ url: '/sales/return', method: 'get', params })
}
export function addSalReturn(data) {
  return request({ url: '/sales/return', method: 'post', data })
}
export function deleteSalReturn(id) {
  return request({ url: '/sales/return/' + id, method: 'delete' })
}
