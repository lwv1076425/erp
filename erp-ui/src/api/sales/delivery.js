import request from '@/utils/request'

export function listSalDelivery(params) {
  return request({ url: '/sales/delivery', method: 'get', params })
}
export function addSalDelivery(data) {
  return request({ url: '/sales/delivery', method: 'post', data })
}
export function deleteSalDelivery(id) {
  return request({ url: '/sales/delivery/' + id, method: 'delete' })
}
