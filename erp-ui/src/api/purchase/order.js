import request from '@/utils/request'

export function listPurOrder(params) {
  return request({ url: '/purchase/order', method: 'get', params })
}
export function getPurOrder(id) {
  return request({ url: '/purchase/order/' + id, method: 'get' })
}
export function addPurOrder(data) {
  return request({ url: '/purchase/order', method: 'post', data })
}
export function updatePurOrder(id, data) {
  return request({ url: '/purchase/order/' + id, method: 'put', data })
}
export function deletePurOrder(id) {
  return request({ url: '/purchase/order/' + id, method: 'delete' })
}
