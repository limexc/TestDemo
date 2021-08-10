import request from '@/utils/request'

export default {


  editroah(roahTVO) {
    return request({
      url: `/sys/roah/editroah`,
      method: 'post',
      //data表示将数据转为json
      data: roahTVO
    })
  },

}
