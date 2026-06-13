import request from '@/utils/request'

export function listCustomer(params) {
  return request({ url: '/base/customer', method: 'get', params })
}
export function getCustomer(id) {
  return request({ url: '/base/customer/' + id, method: 'get' })
}
export function addCustomer(data) {
  return request({ url: '/base/customer', method: 'post', data })
}
export function updateCustomer(id, data) {
  return request({ url: '/base/customer/' + id, method: 'put', data })
}
export function deleteCustomer(id) {
  return request({ url: '/base/customer/' + id, method: 'delete' })
}
