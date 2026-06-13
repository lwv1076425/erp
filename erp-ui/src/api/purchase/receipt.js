import request from '@/utils/request'

export function listPurReceipt(params) {
  return request({ url: '/purchase/receipt', method: 'get', params })
}
export function addPurReceipt(data) {
  return request({ url: '/purchase/receipt', method: 'post', data })
}
export function deletePurReceipt(id) {
  return request({ url: '/purchase/receipt/' + id, method: 'delete' })
}
