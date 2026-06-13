import request from '@/utils/request'

export function listFinInvoice(params) {
  return request({ url: '/finance/invoice', method: 'get', params })
}
export function addFinInvoice(data) {
  return request({ url: '/finance/invoice', method: 'post', data })
}
export function deleteFinInvoice(id) {
  return request({ url: '/finance/invoice/' + id, method: 'delete' })
}
