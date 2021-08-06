<template>
  <el-container>
    <!--头部-->
    <el-header style="text-align: left; font-size: 12px">
      <h1 style="display: inline-block;font-size: 20px">用户权限管理系统</h1>
      <span style="margin-left: 83%"></span>
      <span>{{}}</span><span>，欢迎您。 </span>
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 20px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>登出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>

    <el-container>
      <!--侧边栏-->
      <el-aside width="200px">
        <el-menu class="el-menu-vertical-demo" default-active="2" @select="handleSelect">
          <menu-tree :menuData="menuData"></menu-tree>
        </el-menu>
      </el-aside>





      <el-container>
        <!--主体-->
        <el-main>
          <!---->
          <v-user-manage></v-user-manage>

          <!---->
        </el-main>

        <!--页脚-->
        <el-footer>Footer</el-footer>
      </el-container>

    </el-container>
  </el-container>
</template>


<style>
.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 30px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 20px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  height: 83vh;
  /**line-height: 83vh;**/
}




</style>

<script>

// @ is an alias to /src
//import HelloWorld from '@/components/HelloWorld.vue'

import VUserManage from "../components/UserManage"
import MenuTree from '../components/MenuTree'

export default {
  name: 'Home',
  components: {VUserManage, MenuTree},
  methods:{
    handleSelect(){}
  }
};
</script>
