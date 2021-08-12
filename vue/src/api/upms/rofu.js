import request from '@/utils/request'

export default {


  getAllRole(uid) {
    return request({
      url: `/sys/rofu/findrofu?uid=${uid}`,
      method: 'get',
    })
  },

  deitUserRole(userRoleVo){
    return request({
      url:`/sys/rofu/editrofus`,
      method:'post',
      data:userRoleVo
    })
  }

}
