import request from '@/utils/request'

export function listUnit(params) {
  return request({ url: '/base/unit', method: 'get', params })
}
export function addUnit(data) {
  return request({ url: '/base/unit', method: 'post', data })
}
export function updateUnit(id, data) {
  return request({ url: '/base/unit/' + id, method: 'put', data })
}
export function deleteUnit(id) {
  return request({ url: '/base/unit/' + id, method: 'delete' })
}
