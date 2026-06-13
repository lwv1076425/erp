import request from '@/utils/request'

export function listInvTransfer(params) {
  return request({ url: '/inventory/transfer', method: 'get', params })
}
export function addInvTransfer(data) {
  return request({ url: '/inventory/transfer', method: 'post', data })
}
export function deleteInvTransfer(id) {
  return request({ url: '/inventory/transfer/' + id, method: 'delete' })
}
