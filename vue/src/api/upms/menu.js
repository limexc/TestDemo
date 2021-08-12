import request from '@/utils/request'

export default {

  getMenuListPage(current, limit, upmsMenuTQuery) {
    return request({
      url: `/sys/menu/findmenu?current=${current}&limit=${limit}`,
      method: 'post',
      //data表示将数据转为json
      data: upmsMenuTQuery
    })
  },

  editMenuInfo(upmsMenuT) {
    return request({
      url: `/sys/menu/editmenu`,
      method: 'post',
      //data表示将数据转为json
      data: upmsMenuT
    })
  },

  addMenuInfo(upmsMenuT) {
    return request({
      url: `/sys/menu/addmenu`,
      method: 'post',
      //data表示将数据转为json
      data: upmsMenuT
    })
  },

  delMenuInfo(ids) {
    return request({
      url: `/sys/menu/delmenu?ids=${ids}`,
      method: 'delete',
      //data: ids
    })
  },

  //获取菜单树形列表
  getMenuTreeList(){
    return request({
      url:`/sys/menu/getmenutree`,
      method:'get',
    })
  },

  getMenuTreeListAndId(id){
    return request({
      url:`/sys/menu/menutreeofid?id=${id}`,
      method:'get',
    })
  },

  getUpperMenuList(){
    return request({
      url:`/sys/menu/uppermenu`,
      method:'get',
    })
  }



}
