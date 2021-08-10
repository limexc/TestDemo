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
      <el-form-item label="用户名">
        <el-input v-model="roleInfo.userAlias" :disabled="true" placeholder="用户名"></el-input>
      </el-form-item>

      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          min-width="5%">
        </el-table-column>
        <el-table-column
          label="序号"
          prop="roleId"
          min-width="5%">
        </el-table-column>
        <el-table-column
          label="角色编码"
          prop="roleNo"
          min-width="10%">
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色名称"
          min-width="10%">
        </el-table-column>
        <el-table-column
          label="生效日期"
          min-width="10%">
          <template slot-scope="scope">{{ scope.row.roleStartTime.substring(0,10) }}</template>
        </el-table-column>
        <el-table-column
          label="失效日期"
          min-width="10%">
          <template slot-scope="scope">{{ scope.row.roleStopTime }}</template>
        </el-table-column>
        <el-table-column
          prop="roleStatus"
          label="状态"
          min-width="10%"
          show-overflow-tooltip>
        </el-table-column>
      </el-table>


      <el-form-item style="text-align: right;">
        <el-button type="primary" @click="submitForm('roleInfo')"
        >确定</el-button
        >
        <el-button @click="closeDialog(0)">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
  </el-dialog>
  </transition>
</template>

<script>
import rofu from '@/api/upms/rofu'

export default {
  name: 'setRoleWindow',
  components: {},
  data() {
    return {
      roleInfo:JSON.parse(JSON.stringify(this.itemInfo)),
      tableData:[],
      showDialog: false,
      multipleSelection:[],
      userRoleVo:{
          roleIds: [],
          userId: ""
      },
      rowSelect:[],
    }
  },
  created() {//页面渲染之前执行
    this.getAllRoleTableDate(this.roleInfo)
  },
  props: {
    dialogTitle: {
      type: String,
      default: "设置角色",
    },
    itemInfo: {
      type: Object,
      default: function () {
        return {};
      },
    },
  },
  methods: {

    getAllRoleTableDate(roleInfo){
      rofu.getAllRole(roleInfo.userId)
        .then(response=>{//请求成功
          let reData=new Array();
          let tmpData = response.data.info

          for (let i =0;i<tmpData.length;i++){
            console.info(tmpData[i])
            if (tmpData[i].select){
              //console.info("被选中了:")
              //console.info(tmpData[i].upmsRoleT)
              this.rowSelect.push(tmpData[i].upmsRoleT)
            }
            reData.push(tmpData[i].upmsRoleT)
          }
          let that = this
          this.tableData=reData
          this.$nextTick(() => {
            that.rowSelect.forEach((row) => {
              this.$refs.multipleTable.toggleRowSelection(row,true);
            });
          });
          //console.log(this.tableData)
        })
        .catch(error=>{//请求失败
          console.log(error)
        })
    },

    // 保存操作
    submitForm(formName) {
      const that = this;
      let userRoleVo = that.userRoleVo
      const params = Object.assign(that.roleInfo, {});
      userRoleVo.userId=params.userId

      console.log(params.userId)
      console.log("走修改请求")
      rofu.deitUserRole(userRoleVo).then(
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

    //选中后更新值
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.userRoleVo.roleIds = []
      for (let i=0;i<val.length;i++){
        //console.log(val[i].roleId)
        this.userRoleVo.roleIds.push(val[i].roleId)
      }
      console.log(this.userRoleVo.roleIds)
    }
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
