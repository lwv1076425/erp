import request from '@/utils/request'

export function listProduct(params) {
  return request({ url: '/base/product', method: 'get', params })
}
export function getProduct(id) {
  return request({ url: '/base/product/' + id, method: 'get' })
}
export function addProduct(data) {
  return request({ url: '/base/product', method: 'post', data })
}
export function updateProduct(id, data) {
  return request({ url: '/base/product/' + id, method: 'put', data })
}
export function deleteProduct(id) {
  return request({ url: '/base/product/' + id, method: 'delete' })
}
