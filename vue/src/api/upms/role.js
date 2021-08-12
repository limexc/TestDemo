import request from '@/utils/request'

export default {

  getRoleListPage(current, limit, UpmsRoleTQuery) {
    return request({
      url: `/sys/role/findrole?current=${current}&limit=${limit}`,
      method: 'post',
      //data表示将数据转为json
      data: UpmsRoleTQuery
    })
  },

  editRoleInfo(upmsRoleT) {
    return request({
      url: `/sys/role/editrole`,
      method: 'post',
      //data表示将数据转为json
      data: upmsRoleT
    })
  },

  addRoleInfo(upmsRoleT) {
    return request({
      url: `/sys/role/addrole`,
      method: 'post',
      //data表示将数据转为json
      data: upmsRoleT
    })
  },

  delRoleInfo(id) {
    return request({
      url: `/sys/role/delrole?id=${id}`,
      method: 'delete'
    })
  },
  delRolesInfo(ids) {
    return request({
      url: `/sys/role/delroles`,
      method: 'delete',
      data: ids
    })
  }

}
