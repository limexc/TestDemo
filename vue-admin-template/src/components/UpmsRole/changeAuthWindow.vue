<!--一个组件多用，添加/编辑弹窗-->
<template>
  <transition name="dialog-fade">
  <el-dialog
    v-if="showDialog"
    :title="dialogTitle"
    class="dialog-component"
    :visible.sync="showDialog"
    width="700px"
    @close="closeDialog(0)"
  >
  <div class="mycontainer">
    <el-form ref="roleInfo" :model="roleInfo" label-width="80px">

      <el-form-item label="角色名称">
        <el-input  v-model="roleInfo.roleName" :disabled="true" placeholder="角色名称"></el-input>
      </el-form-item>
      <!--权限下拉列表-->
      <el-form-item label="权限设置">
      <el-tree ref="menuTreeDateInfo"
        style="margin-top: 10px"
        :data="menuTreeDateInfo"
        show-checkbox
        node-key="menuId"
        :default-checked-keys="checkedKey"
        default-expand-all
        :props="defaultProps"
        @check-change="handleCheckChange">
      </el-tree>
      </el-form-item>

      <el-form-item style="text-align: right;">
        <el-button type="primary" @click="submitForm('roleInfo')">确定</el-button>
        <el-button @click="closeDialog(0)">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
  </el-dialog>
  </transition>
</template>

<script>

import menu from '@/api/upms/menu'
import roah from '@/api/upms/roah'

export default {
  name: 'changeAuthWindow',
  components: {},
  data() {
    return {
      roleInfo:JSON.parse(JSON.stringify(this.itemInfo)),
      showDialog: false,
      checkedKey:[],
      menuTreeDateInfo: [],
      roahTVO:{
        authIds:[],
        roleId:""
      },

      defaultProps: {
        id:"menuId",
        children: 'children',
        label: 'menuName',
      },

    }
  },
  props: {
    dialogTitle: {
      type: String,
      default: "设置角色权限",
    },
    itemInfo: {
      type: Object,
      default: function () {
        return {};
      },
    },
  },
  created() {
    this.getMenuTreeDate(this.roleInfo.roleId);
  },
  methods: {
    getMenuTreeDate(roleId){
      let that = this
      menu.getMenuTreeListAndId(roleId)
        .then(response=>{//请求成功
          this.menuTreeDateInfo = response.data.info
          let str = response.data.msg
          let str1 = str.replace(/\[|]/g,"")
          let str2 = str1.replace(/\s*/g,"")
          let arr =str2.split(',')
          //console.log(arr)
          //this.checkedKey = arr
          this.$refs["menuTreeDateInfo"].setCheckedKeys(arr)
          //console.log(this.checkedKey)
        })
        .catch(error=>{//请求失败
          console.log(error)
        })
    },
    //获得选中的节点信息
    handleCheckChange(){
      this.roahTVO.authIds=[]
      let res = this.$refs["menuTreeDateInfo"].getCheckedNodes()
      for (let i=0;i<res.length;i++){
        this.roahTVO.authIds.push(res[i].menuId)
      }
      console.log(this.roahTVO.authIds)
    },
    // 保存操作
    submitForm(formName) {
      const that = this;
      const params = Object.assign(that.roleInfo, {});
      //console.log(params.roleId)
      that.roahTVO.roleId =params.roleId
      roah.editroah(that.roahTVO).then(
        that.$message({
          message: "操作成功！",
          type: "success",
        })
      )
      that.closeDialog(1);
    },
    // 关闭弹框
    closeDialog(flag) {
      this.$refs["roleInfo"].resetFields();
      this.showDialog = false;
      this.$emit("closeDialog", flag);
    },
  },
}
</script>
<style>
.mycontainer {
  display: flex;
  flex-direction: column;
  justify-content: space-around
}
</style>
