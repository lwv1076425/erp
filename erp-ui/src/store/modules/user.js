import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { login, logout, getInfo } from '@/api/system/auth'

export const useUserStore = defineStore('user', () => {
  const token = ref(getToken())
  const userInfo = ref({})
  const roles = ref([])
  const permissions = ref([])

  // 登录
  async function loginAction(loginForm) {
    const res = await login(loginForm)
    if (res.code === 200) {
      token.value = res.data.token
      setToken(res.data.token)
    }
    return res
  }

  // 获取用户信息
  async function getInfoAction() {
    const res = await getInfo()
    if (res.code === 200) {
      userInfo.value = res.data.user
      roles.value = res.data.roles
      permissions.value = res.data.permissions
    }
    return res
  }

  // 退出登录
  async function logoutAction() {
    await logout()
    token.value = ''
    roles.value = []
    permissions.value = []
    removeToken()
  }

  // 重置 Token
  function resetToken() {
    token.value = ''
    roles.value = []
    permissions.value = []
    removeToken()
  }

  return {
    token,
    userInfo,
    roles,
    permissions,
    loginAction,
    getInfoAction,
    logoutAction,
    resetToken
  }
})
