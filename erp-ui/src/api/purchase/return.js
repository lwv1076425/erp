import request from '@/utils/request'

export function listPurReturn(params) {
  return request({ url: '/purchase/return', method: 'get', params })
}
export function addPurReturn(data) {
  return request({ url: '/purchase/return', method: 'post', data })
}
export function deletePurReturn(id) {
  return request({ url: '/purchase/return/' + id, method: 'delete' })
}
