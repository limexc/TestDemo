import request from '@/utils/request'

export default {

  getUserListPage(current, limit, userQuery) {
    return request({
      url: `/sys/user/finduser?current=${current}&limit=${limit}`,
      method: 'post',
      //data表示将数据转为json
      data: userQuery
    })
  },

  editUserInfo(upmsUserT) {
    return request({
      url: `/sys/user/edituser`,
      method: 'post',
      //data表示将数据转为json
      data: upmsUserT
    })
  },

  addUserInfo(addUserVo) {
    return request({
      url: `/sys/user/adduser`,
      method: 'post',
      //data表示将数据转为json
      data: addUserVo
    })
  },

  delUsersInfo(ids) {
    return request({
      url: `/sys/user/delusers`,
      method: 'delete',
      data: ids
    })
  },
  delUserInfo(id) {
    return request({
      url: `/sys/user/deluser?id=${id}`,
      method: 'delete',
    })
  }

}
