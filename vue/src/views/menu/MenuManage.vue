<template>
  <div>
    <div style="margin-top: 10px;margin-left: 10px;">

      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
         <el-input v-model="menuQuery.menuName" placeholder="菜单名称"/>
        </el-form-item>

        <el-form-item>
          <el-select v-model="menuQuery.menuStatus" clearable placeholder="状态">
            <el-option v-for="item in menuStatus"
                       :key="item.label"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-select v-model="menuQuery.menuType" clearable placeholder="菜单类型">
            <el-option v-for="item in menuTypes"
                       :key="item.label"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>


        <el-button type="primary" icon="el-icon-search" @click="getMenuListData()">查询</el-button>

        <el-button type="primary" @click="addUpmsMenu()">新增</el-button>
        <!--<el-button type="danger" @click="deleteMenu()">删除</el-button>-->
      </el-form>
    </div>

    <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      empty-text = "暂无数据！"
      style="width: 100%;height: 90%"
      @selection-change="handleSelectionChange">
      <!--<el-table-column type="selection" min-width="5%"/>-->
      <el-table-column label="ID" prop="menuId" min-width="5%"/>
      <el-table-column label="菜单名称" prop="menuName" min-width="10%"/>
      <el-table-column label="上级菜单" prop="menuUpper"  min-width="10%"/>
      <el-table-column label="菜单类型" prop="menuType" min-width="5%"/>
      <el-table-column label="菜单标识" prop="menuFlag" min-width="10%"/>
      <el-table-column label="菜单地址" prop="menuUrl" min-width="10%"/>
      <el-table-column label="图标" min-width="15%">
        <template scope="scope">
          <div class="demo-image__preview">
            <el-image  style="width: 20px; height: 20px; margin-left:10px;"
                       v-for="item in scope.row.menuIco"
                       :src="item"
                       :preview-src-list="scope.row.menuIco"
            ></el-image>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="排序号" prop="menuSerial" min-width="15%"/>
      <el-table-column label="状态" min-width="10%">
        <template slot-scope="scope">{{scope.row.menuStatus==='Act'?'有效':'无效'}}</template>
      </el-table-column>
      <el-table-column label="操作" min-width="20%">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click.native.prevent="menuInfoEdit(scope.$index, scope.row)">
            修改
          </el-button>
          <el-button
            type="text"
            size="small"
            @click.native.prevent="deleteMenu(scope.$index, scope.row)">
            删除
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

    <el-pagination
      @current-change="getMenuListData"
      :current-page="page"
      :page-size="limit"
      layout="total, prev, pager, next, jumper"
      style="padding: 30px 0;text-align: center;"
      :total="total">
    </el-pagination>
  </div>

</template>

<script>


import menu from '@/api/upms/menu'
import ActivityWindow from '@/components/UpmsMenu/activityWindow'

export default {
  name: 'MenuManage',
  components: { ActivityWindow },
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

      menuQuery:{
        menuType:"",
        menuName: "",
        menuStatus: ""
      },//条件封装对象

      tableItem:{
        menuUpper:"",
        menuName: "",
        menuFlag:"",
        menuStatus: "",
        menuType:"",
        menuUrl:"",
        menuSerial:"",
        menuIco:"",
        menuNote:""
      },
      menuStatus: [{
        value: '',
        label: '全部'
      }, {
        value: 'Act',
        label: '有效'
      }, {
        value: 'Del',
        label: '无效'
      }],

      menuTypes: [{
        value: '',
        label: '全部'
      }, {
        value: '目录',
        label: '目录'
      }, {
        value: '菜单',
        label: '菜单'
      }, {
        value: '功能',
        label: '功能'
      }],

    }
  },

  created() {//页面渲染之前执行
    this.getMenuListData()
  },

  methods: {//具体的方法
    getMenuListData(page=1) {
      this.page=page
      menu.getMenuListPage(this.page,this.limit,this.menuQuery)
        .then(response=>{//请求成功
          this.tableData = response.data.info
          this.total = parseInt(response.data.msg)
        })
        .catch(error=>{//请求失败
          console.log(error)
        })
    },
    addUpmsMenu(){
      this.tableItem = {
        menuUpper:"",
        menuName: "",
        menuFlag:"",
        menuStatus: "",
        menuType:"",
        menuUrl:"",
        menuSerial:"",
        menuIco:"",
        menuNote:""
      }
      this.dialogTitle = "添加菜单";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["activityWindow"].showDialog = true;
      });
    },
    menuInfoEdit(index, row) {
      //row当前行数据,包含所有
      this.tableItem = row;
      this.dialogTitle = "编辑菜单";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["activityWindow"].showDialog = true;
      });

    },
    deleteMenu(index, row) {
      this.$confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //判断有没有index,这个决定是点了哪一个删除
        if(index!==undefined){
          menu.delMenuInfo(row.menuId)
        }else {
          menu.delMenuInfo(this.tableChecked)
        }
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).finally(()=>{
        this.getMenuListData();
      })
    },

    closeDialog(flag) {
      if (flag) {
        console.log("重新刷新表格内容")
        this.getMenuListData();
      }
      this.showDialog = false;
    },
    //多行选择
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.tableChecked=[]
      //此val内包含所有选中项
      for (let i=0;i<val.length;i++){
        this.tableChecked.push(val[i].menuId)
      }
      console.log(this.tableChecked)
    },

  }
}
</script>
