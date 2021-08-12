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
    <el-form ref="userInfo" :model="userInfo" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="userInfo.userAlias"  placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input  v-model="userInfo.userName" placeholder="真实姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="userInfo.userGender" clearable placeholder="性别">
          <el-option v-for="item in options"
                     :key="item.label"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="密码">
        <el-input  v-model="userInfo.userPasswd1"type="password" placeholder="输入密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码">
        <el-input  v-model="userInfo.userPasswd2" type="password" placeholder="确认密码"></el-input>
      </el-form-item>

      <el-form-item label="电话">
        <el-input  v-model="userInfo.userTel" type="number"  placeholder="输入电话,非必填"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input  v-model="userInfo.userEmail"  type="email" placeholder="输入邮箱,非必填"></el-input>
      </el-form-item>

      <el-form-item label="状态">
        <el-radio-group v-model="userInfo.userStatus">
          <el-radio label="Act" value="Act">有效</el-radio>
          <el-radio label="Del" value="Del">无效</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="userInfo.userNote"></el-input>
      </el-form-item>
      <el-form-item style="text-align: right;">
        <el-button type="primary" @click="submitForm('userInfo')"
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
import user from '@/api/upms/user'

export default {
  name: 'ActivityWindow',
  components: {},

  data() {

    return {
      userInfo:JSON.parse(JSON.stringify(this.itemInfo)),
      options: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }],


      showDialog: false,
    }

  },
  props: {
    dialogTitle: {
      type: String,
      default: "添加人员",
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
      const params = Object.assign(that.userInfo, {});
      console.log(params.userId)
      if (params.userId!==undefined){
        console.log("走修改请求")
        user.editUserInfo(params).then(
          that.$message({
            message: "操作成功！",
            type: "success",
          })
        )
        that.closeDialog(1);
      }else {
        console.log("走新增用户")
        user.addUserInfo(params).then(
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
      this.$refs["userInfo"].resetFields();
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
