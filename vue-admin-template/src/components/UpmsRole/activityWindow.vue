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
      <el-form-item label="角色编码">
        <el-input v-model="roleInfo.roleNo" placeholder="角色编码"></el-input>
      </el-form-item>
      <el-form-item label="角色名称">
        <el-input  v-model="roleInfo.roleName" placeholder="角色名称"></el-input>
      </el-form-item>

      <el-form-item label="生效日期">
        <el-date-picker
          v-model="roleInfo.roleStartTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        ></el-date-picker>
      </el-form-item>

      <el-form-item label="失效日期">
        <el-date-picker
          v-model="roleInfo.roleStopTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        ></el-date-picker>
      </el-form-item>

      <el-form-item label="状态">
        <el-radio-group v-model="roleInfo.roleStatus">
          <el-radio label="Act" value="Act">有效</el-radio>
          <el-radio label="Del" value="Del">无效</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="roleInfo.roleNote"></el-input>
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
import role from '@/api/upms/role'

export default {
  name: 'ActivityWindow',
  components: {},
  data() {
    return {
      roleInfo:JSON.parse(JSON.stringify(this.itemInfo)),
      showDialog: false,
    }
  },
  props: {
    dialogTitle: {
      type: String,
      default: "添加角色",
    },
    itemInfo: {
      type: Object,
      default: function () {
        return {};
      },
    },
  },
  methods: {
    // 保存操作
    submitForm(formName) {
      const that = this;
      const params = Object.assign(that.roleInfo, {});
      //console.log(params.userId)
      if (params.roleId!==undefined){
        console.log("走修改")
        role.editRoleInfo(params).then(
          that.$message({
            message: "操作成功！",
            type: "success",
          })
        )
        that.closeDialog(1);
      }else {
        console.log("走新增")
        role.addRoleInfo(params).then(
          that.$message({
            message: "操作成功！",
            type: "success",
          })
        )
        that.closeDialog(1);
      }

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
