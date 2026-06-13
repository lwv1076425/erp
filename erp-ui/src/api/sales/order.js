import request from '@/utils/request'

export function listSalOrder(params) {
  return request({ url: '/sales/order', method: 'get', params })
}
export function getSalOrder(id) {
  return request({ url: '/sales/order/' + id, method: 'get' })
}
export function addSalOrder(data) {
  return request({ url: '/sales/order', method: 'post', data })
}
export function updateSalOrder(id, data) {
  return request({ url: '/sales/order/' + id, method: 'put', data })
}
export function deleteSalOrder(id) {
  return request({ url: '/sales/order/' + id, method: 'delete' })
}
