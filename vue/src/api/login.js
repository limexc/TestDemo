import request from '@/utils/request'

//登录
export function login(userAlias, userPasswd) {
  return request({
    url: '/sys/api/user/login',
    method: 'post',
    data: {
      userAlias,
      userPasswd
    }
  })
}

//获取用户信息
export function getInfo(token) {
  return request({
    url: '/sys/api/info',
    method: 'get',
    token:  { token }
  })
}

//登出
export function logout() {
  return request({
    url: '/sys/api/logout',
    method: 'post'
  })
}

// 获取菜单权限数据  此接口未完成
export function getMenu() {
  return request({
    url: '/sys/api/menu',
    method: 'get'
  })
}
