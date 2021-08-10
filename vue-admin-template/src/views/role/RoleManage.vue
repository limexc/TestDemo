<template>
  <div>
    <div style="margin-top: 10px">

      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
         <el-input v-model="UpmsRoleTQuery.roleName" placeholder="名称"/>
        </el-form-item>

        <el-form-item>
          <el-select v-model="UpmsRoleTQuery.roleStatus" clearable placeholder="状态">
            <el-option v-for="item in options"
                       :key="item.label"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getRoleListData()">查询</el-button>

        <el-button type="primary" @click="addUpmsRole()">新增</el-button>
        <el-button type="danger" @click="deleteRole()">删除</el-button>
      </el-form>
    </div>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      empty-text = "暂无数据！"
      style="width: 100%;height: 90%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" label="全选" min-width="5%"/>
      <el-table-column label="序号" prop="roleId" min-width="5%"/>
      <el-table-column label="角色编码" prop="roleNo" min-width="10%"/>
      <el-table-column label="角色名称" prop="roleName"  min-width="10%"/>
      <el-table-column label="生效日期" prop="roleStartTime" min-width="15%"/>
      <el-table-column label="失效日期" prop="roleStopTime" min-width="15%"/>
      <el-table-column label="状态" min-width="10%">
        <template slot-scope="scope">{{scope.row.roleStatus==='Act'?'有效':'无效'}}</template>
      </el-table-column>
      <el-table-column label="操作" min-width="20%">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click.native.prevent="roleInfoEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.native.prevent="deleteRole(scope.$index, scope.row)">
            删除
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.native.prevent="changeRoleAuth(scope.$index, scope.row)">
            设置角色权限
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--弹框组件开始-----------------------start-->
    <activity-window
      v-if="showDialog"
      ref="activityWindow"
      :dialog-title="dialogTitle"
      :item-info="tableItem"
      @closeDialog="closeDialog"
    ></activity-window>

    <change-auth-window
      v-if="showDialog"
      ref="changeAuthWindow"
      :dialog-title="dialogTitle2"
      :item-info="tableItem"
      @closeDialog="closeDialog"
    ></change-auth-window>
    <!--弹框组件开始-----------------------end-->

    <el-pagination
      @current-change="getRoleListData"
      :current-page="page"
      :page-size="limit"
      layout="total, prev, pager, next, jumper"
      style="padding: 30px 0;text-align: center;"
      :total="total">
    </el-pagination>
  </div>

</template>

<script>


import role from '@/api/upms/role'
import ActivityWindow from '@/components/UpmsRole/activityWindow'
import changeAuthWindow from '@/components/UpmsRole/changeAuthWindow'

export default {
  name: 'RoleManage',
  components: { ActivityWindow ,changeAuthWindow},
  data() {//定义变量和初始值 与 data: 一样
    return {
      multipleSelection:0,

      showDialog: false,
      dialogTitle: "添加角色",
      dialogTitle2:"修改角色权限",
      tableChecked: [],//选中的行
      tableData: null,//查询之后接口返回集合
      page: 1, //当前页
      limit: 10,//每页记录数
      total: 0,//总记录数

      UpmsRoleTQuery:{
        roleName: "",
        roleStatus: ""
      },//条件封装对象

      tableItem:{
        roleId:"",
        roleName: "",
        roleStatus: "",
        roleStartTime:"",
        roleStopTime:"",
        roleNo:"",
        roleNote:""
      },
      options: [{
        value: '',
        label: '全部'
      }, {
        value: 'Act',
        label: '有效'
      }, {
        value: 'Del',
        label: '无效'
      }],

    }
  },

  created() {//页面渲染之前执行
    this.getRoleListData()
  },

  methods: {//具体的方法
    getRoleListData(page=1) {
      this.page=page
      role.getRoleListPage(this.page,this.limit,this.UpmsRoleTQuery)
        .then(response=>{//请求成功
          this.tableData = response.data.info
          this.total = parseInt(response.data.msg)
        })
        .catch(error=>{//请求失败
          console.log(error)
        })
    },
    addUpmsRole(){
      this.tableItem = {
        roleName: "",
        roleStatus: "",
        roleStartTime:"",
        roleStopTime:"",
        roleNo:"",
        roleNote:""
      }
      this.dialogTitle = "添加角色";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["activityWindow"].showDialog = true;
      });
    },
    roleInfoEdit(index, row) {
      //row当前行数据,包含所有
      this.tableItem = row;
      this.dialogTitle = "编辑角色";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["activityWindow"].showDialog = true;
      });

    },
    deleteRole(index, row) {
      this.$confirm('此操作将永久删除该角色, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //判断有没有index,这个决定是点了哪一个删除
        if(index!==undefined){
          role.delRoleInfo(row.roleId)
        }else {
          role.delRolesInfo(this.tableChecked)
        }
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      }).finally(()=>{
        this.getRoleListData();
      })
    },

    closeDialog(flag) {
      if (flag) {
        //console.log("重新刷新表格内容")
        this.getRoleListData();
      }
      this.showDialog = false;
    },
    //多行选择
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.tableChecked=[]
      //此val内包含所有选中项
      for (let i=0;i<val.length;i++){
        this.tableChecked.push(val[i].roleId)
      }
      console.log(this.tableChecked)
    },
    //修改角色权限
    changeRoleAuth(index,row){
      //row当前行数据,包含所有
      this.tableItem = row;
      this.dialogTitle = "编辑角色权限";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["changeAuthWindow"].showDialog = true;
      });
    }

  }
}
</script>
