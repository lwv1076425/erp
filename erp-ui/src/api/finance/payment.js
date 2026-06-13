import request from '@/utils/request'

export function listFinPayment(params) {
  return request({ url: '/finance/payment', method: 'get', params })
}
export function addFinPayment(data) {
  return request({ url: '/finance/payment', method: 'post', data })
}
export function deleteFinPayment(id) {
  return request({ url: '/finance/payment/' + id, method: 'delete' })
}
