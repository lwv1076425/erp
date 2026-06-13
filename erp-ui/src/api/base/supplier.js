import request from '@/utils/request'

export function listSupplier(params) {
  return request({ url: '/base/supplier', method: 'get', params })
}
export function getSupplier(id) {
  return request({ url: '/base/supplier/' + id, method: 'get' })
}
export function addSupplier(data) {
  return request({ url: '/base/supplier', method: 'post', data })
}
export function updateSupplier(id, data) {
  return request({ url: '/base/supplier/' + id, method: 'put', data })
}
export function deleteSupplier(id) {
  return request({ url: '/base/supplier/' + id, method: 'delete' })
}
