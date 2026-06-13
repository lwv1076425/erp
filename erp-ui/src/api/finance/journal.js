import request from '@/utils/request'

export function listFinJournal(params) {
  return request({ url: '/finance/journal', method: 'get', params })
}
export function addFinJournal(data) {
  return request({ url: '/finance/journal', method: 'post', data })
}
export function deleteFinJournal(id) {
  return request({ url: '/finance/journal/' + id, method: 'delete' })
}
