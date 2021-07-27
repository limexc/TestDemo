<template>
  <el-container>
    <!--头部-->
    <el-header style="text-align: left; font-size: 12px">
      <h1 style="display: inline-block;font-size: 20px">用户权限管理系统</h1>
      <span style="margin-left: 83%"></span>
      <span>王小虎</span><span>，欢迎您。 </span>
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
        <v-menu navigation-item v-for="(menu,i) in menu" :key="i" :item="menu"></v-menu>
      </el-aside>

      <el-container>
        <!--主体-->
        <el-main>
          <!---->
          <v-user-manage></v-user-manage>
          <template>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage4"
                :page-sizes="[5, 10, 15]"
                :page-size="10"
                layout="total, sizes, prev, pager, next, jumper"
                :total="40">
            </el-pagination>
          </template>
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


import VMenu from "../components/Menu";
import VUserManage from "../components/UserManage"

export default {
  name: 'Home',
  components: {VUserManage, VMenu},
  data() {
    return{
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
};
</script>
