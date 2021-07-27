<template>
  <div>
  <el-input v-model="tableDataname" placeholder="请输入姓名" style="width:240px"></el-input>
  <el-button type="primary" @click="doFilter">搜索</el-button>

    <el-select v-model="value" placeholder="请选择">
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
      </el-option>
    </el-select>

  <el-date-picker
      v-model="value1"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期">
  </el-date-picker>



  <el-table
      ref="multipleTable"
      :data="tableData"
      tooltip-effect="dark"
      fit= true
      stripe =true
      border = true
      empty-text = "暂无数据！"
      style="width: 100%;height: 90%"
      @selection-change="handleSelectionChange">
    <el-table-column
        type="selection"
        min-width="5%">
    </el-table-column>
    <el-table-column
      label="序号"
      prop="userinfoId"
      min-width="5%">
    </el-table-column>
    <el-table-column
        label="用户名"
        prop="userinfoAlias"
        min-width="10%">
    </el-table-column>

    <el-table-column
        prop="userinfoName"
        label="姓名"
        min-width="10%">
    </el-table-column>

    <el-table-column
        label="性别"
        prop="userinfoGender"
        min-width="10%">
    </el-table-column>

    <el-table-column
        label="联系电话"
        prop="userinfoTel"
        min-width="10%">
    </el-table-column>
    <el-table-column
        label="邮箱"
        prop="userinfoEmail"
        min-width="20%">
    </el-table-column>


    <el-table-column
        label="创建时间"
        prop="creationDate"
        min-width="10%">
    </el-table-column>

    <el-table-column
        label="最后修改时间"
        min-width="10%"
        prop="lastUpdatedDate">
    </el-table-column>

    <el-table-column
        label="状态"
        prop="userinfoStatus"
        min-width="10%">
    </el-table-column>

    <el-table-column
        label="操作"
        min-width="20%">
      <template slot-scope="scope">

        <el-button
            @click.native.prevent="userInfoEdit(scope.$index,  scope.row)"
            type="text"
            size="small">
          修改
        </el-button>

        <el-button
            @click.native.prevent="deleteUser(scope.$index, scope.row)"
            type="text"
            size="small">
          删除
        </el-button>

        <el-button
            @click.native.prevent="deleteRow(scope.$index, tableData)"
            type="text"
            size="small">
          有效/无效
        </el-button>

        <el-button
            @click.native.prevent="deleteRow(scope.$index, tableData)"
            type="text"
            size="small">
          设置角色
        </el-button>
      </template>
    </el-table-column>

  </el-table>
  </div>
</template>



<script>
export default {
  name: 'v-user-manage',
  created(){
    let _this = this
    axios.get('/userinfo/userList').then(function (response){
      console.log(response)
      _this.tableData = response.data.data
    })
  },
  data() {
    return {
      tableData: null,
      multipleSelection: [],


      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      value1: '',
      value2: '',




      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      value: ''


    }
  },

  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val)
    },
    deleteUser(index, row) {
      //console.log(row.id)
      //rows.splice(index, 1);
    },
    userInfoEdit(object){

    },






  }
}
</script>