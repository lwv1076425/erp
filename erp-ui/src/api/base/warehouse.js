import request from '@/utils/request'

export function listWarehouse(params) {
  return request({ url: '/base/warehouse', method: 'get', params })
}
export function getWarehouse(id) {
  return request({ url: '/base/warehouse/' + id, method: 'get' })
}
export function addWarehouse(data) {
  return request({ url: '/base/warehouse', method: 'post', data })
}
export function updateWarehouse(id, data) {
  return request({ url: '/base/warehouse/' + id, method: 'put', data })
}
export function deleteWarehouse(id) {
  return request({ url: '/base/warehouse/' + id, method: 'delete' })
}
