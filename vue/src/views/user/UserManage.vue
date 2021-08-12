<template>
  <div>
    <div style="margin-top: 10px">

      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
         <el-input v-model="userQuery.userAlias" placeholder="用户名"/>
        </el-form-item>
        <el-form-item>
          <el-input v-model="userQuery.userName" placeholder="姓名"/>
        </el-form-item>

        <el-form-item>
          <el-select v-model="userQuery.userStatus" clearable placeholder="状态">
            <el-option v-for="item in options"
                       :key="item.label"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="添加时间">
          <el-date-picker
            v-model="userQuery.beginDate"
            type="datetime"
            placeholder="选择开始时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="userQuery.endDate"
            type="datetime"
            placeholder="选择截止时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getUserListData()">查询</el-button>

        <el-button type="primary" @click="addUpmsUser()">新增</el-button>
        <el-button type="danger" @click="deleteUser()">删除</el-button>
      </el-form>
    </div>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      empty-text = "暂无数据！"
      style="width: 100%;height: 90%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" min-width="5%"/>
      <el-table-column label="ID" prop="userId" min-width="5%"/>
      <el-table-column label="用户名" prop="userAlias" min-width="10%"/>
      <el-table-column prop="userName" label="姓名" min-width="10%"/>
      <el-table-column label="性别" prop="userGender" min-width="5%"/>
      <el-table-column label="联系电话" prop="userTel" min-width="10%"/>
      <el-table-column label="邮箱" prop="userEmail" min-width="15%"/>
      <el-table-column label="创建时间" prop="creationDate" min-width="15%"/>
      <el-table-column label="最后修改时间" min-width="15%" prop="lastUpdatedDate"/>
      <el-table-column label="状态" min-width="10%">
        <template slot-scope="scope">{{scope.row.userStatus==='Act'?'有效':'无效'}}</template>
      </el-table-column>
      <el-table-column label="操作" min-width="20%">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click.native.prevent="userInfoEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.native.prevent="deleteUser(scope.$index, scope.row)">
            删除
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.native.prevent="changeStatus(scope.$index, scope.row)">
            {{scope.row.userStatus==='Act'?'无效':'有效'}}
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.native.prevent="setRole(scope.$index, scope.row)">
            设置角色
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
    <!--弹框组件开始-----------------------end-->
    <!--弹框组件开始-----------------------start-->
    <set-role-window
      v-if="showDialog"
      ref="setRoleWindow"
      :dialog-title="dialogTitle"
      :item-info="tableItem"
      @closeDialog="closeDialog"
    ></set-role-window>
    <!--弹框组件开始-----------------------end-->

    <el-pagination
      @current-change="getUserListData"
      :current-page="page"
      :page-size="limit"
      layout="total, prev, pager, next, jumper"
      style="padding: 30px 0;text-align: center;"
      :total="total">
    </el-pagination>
  </div>

</template>

<script>


import user from '@/api/upms/user'
import ActivityWindow from '@/components/UpmsUser/activityWindow'
import setRoleWindow from '@/components/UpmsUser/setRoleWindow'

export default {
  name: 'UserManage',
  components: { ActivityWindow,setRoleWindow },
  data() {//定义变量和初始值 与 data: 一样
    return {
      multipleSelection:0,

      showDialog: false,
      dialogTitle: "添加人员",
      tableChecked: [],//选中的行
      tableData: null,//查询之后接口返回集合
      page: 1, //当前页
      limit: 10,//每页记录数
      total: 0,//总记录数
      userQuery:{
        beginDate: "",
        endDate: "",
        userAlias: "",
        userName: "",
        userStatus: ""
      },//条件封装对象
      tableItem:{
        userAlias: "",
        userName: "",
        userStatus: "",
        userGender:"",
        userEmail:"",
        userTel:"",
        userNote:""
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
    this.getUserListData()
  },

  methods: {//具体的方法
    getUserListData(page=1) {
      this.page=page
      user.getUserListPage(this.page,this.limit,this.userQuery)
        .then(response=>{//请求成功
          this.tableData = response.data.info
          this.total = parseInt(response.data.msg)
        })
        .catch(error=>{//请求失败
          console.log(error)
        })
    },
    addUpmsUser(){
      console.log("新增用户")
      this.tableItem = {
        userAlias: "",
        userName: "",
        userStatus: "",
        userGender:"",
        userEmail:"",
        userTel:"",
        userPasswd1:"",
        userPasswd2:"",
        userNote:""
      }
      this.dialogTitle = "新增用户";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["activityWindow"].showDialog = true;
      });
    },
    userInfoEdit(index, row) {
      //row当前行数据,包含所有
      this.tableItem = row;
      this.dialogTitle = "编辑人员";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["activityWindow"].showDialog = true;
      });

    },
    deleteUser(index, row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //判断有没有index,这个决定是点了哪一个删除
        if(index!==undefined){
          user.delUserInfo(row.userId)
        }else {
          user.delUsersInfo(this.tableChecked)
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
        this.getUserListData();
      })
    },

    closeDialog(flag) {
      if (flag) {
        //console.log("重新刷新表格内容")
        this.tableData = this.getUserListData();
      }
      this.showDialog = false;
    },
    //多行选择
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.tableChecked=[]
      //此val内包含所有选中项
      for (let i=0;i<val.length;i++){
        this.tableChecked.push(val[i].userId)
      }
      console.log(this.tableChecked)
    },
    changeStatus(index,row){
      let that = this
      this.$confirm('是否确认此操作?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let ststus =  row.userStatus==='Act'?'Del':'Act'
        console.log(ststus)
        user.editUserInfo({
            userId:row.userId,
            userStatus:ststus
          }
        )
        this.$message({
          type: 'success',
          message: '修改成功!'
        })

      }).finally(()=>{
        console.log("刷新表格")
        that.tableData = that.getUserListData()
      })
    },

    setRole(index,row){

      //row当前行数据,包含所有
      this.tableItem = row;
      this.dialogTitle = "设置角色";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["setRoleWindow"].showDialog = true;
      });
    }

  }
}
</script>
