import request from '@/utils/request'

export function listInvStock(params) {
  return request({ url: '/inventory/stock', method: 'get', params })
}
