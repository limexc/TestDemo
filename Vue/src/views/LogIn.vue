<template>
  <div class="login-container">
    <el-form :model="form" :rules="rules" status-icon ref="loginForm" label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">

      <h3 class="title" style="font-size: 24px;padding-bottom: 30px">用户权限管理系统</h3>

      <el-form-item prop="username">
        <el-input type="text"
                  v-model="form.username"
                  auto-complete="off"
                  placeholder="用户名"
        ></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input type="password"
                  v-model="form.password"
                  auto-complete="off"
                  placeholder="密码"
        ></el-input>
      </el-form-item>

      <el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox>

      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="onSubmit('loginForm')">登录</el-button>
      </el-form-item>

    </el-form>

    <el-dialog
        title="温馨提示"
        :visible.sync="dialogVisible"
        width="30%"
    >
      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "LogIn",
  data(){
    return {
      form: {
        username: '',
        password: ''
      },
      rules: {
        username: [{required: true, message: 'please enter your account', trigger: 'blur'}],
        password: [{required: true, message: 'enter your password', trigger: 'blur'}]
      },
      checked: false,
      // 对话框显示和隐藏
      dialogVisible: false
    }

  },
  methods: {
    onSubmit(formName) {
      // 为表单绑定验证功能
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
          //this.$router.push("/main/"+this.form.username);
          this.$router.push("/");
        } else {
          this.dialogVisible = true;
          return false;
        }
      });
    },



    handleSubmit(event){
      this.$refs.form.validate((valid) => {
        if(valid){
          this.logining = true;
          if(this.form.username === 'admin' &&
              this.form.password === '123456'){
            this.logining = false;
            sessionStorage.setItem('user', this.form.username);
            this.$router.push({path: '/'});
          }else{
            this.logining = false;
            this.$alert('username or password wrong!', 'info', {
              confirmButtonText: 'ok'
            })
          }
        }else{
          console.log('error submit!');
          return false;
        }
      })
    }
  }
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>