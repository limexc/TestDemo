<template>
  <div>
    <div style="margin-top: -10px">
      <el-input v-model="tableDataname" placeholder="请输入姓名" style="width:240px;padding: 10px"></el-input>

      <el-select v-model="selectValue" placeholder="请选择" style="padding: 10px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      {{selectValue}}
      <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
      </el-date-picker>
      <el-button type="primary" @click="doFilter" style="padding: 10px;margin-left: 10px">搜索</el-button>
    </div>
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
      label="ID"
      prop="userId"
      min-width="5%">
    </el-table-column>
    <el-table-column
        label="用户名"
        prop="userAlias"
        min-width="10%">
    </el-table-column>

    <el-table-column
        prop="userName"
        label="姓名"
        min-width="10%">
    </el-table-column>

    <el-table-column
        label="性别"
        prop="userGender"
        min-width="5%">
    </el-table-column>

    <el-table-column
        label="联系电话"
        prop="userTel"
        min-width="10%">
    </el-table-column>
    <el-table-column
        label="邮箱"
        prop="userEmail"
        min-width="15%">
    </el-table-column>


    <el-table-column
        label="创建时间"
        prop="creationDate"
        min-width="15%">
    </el-table-column>

    <el-table-column
        label="最后修改时间"
        min-width="15%"
        prop="lastUpdatedDate">
    </el-table-column>

    <el-table-column
        label="状态"
        prop="userStatus"
        min-width="10%">
    </el-table-column>

    <el-table-column
        label="操作"
        min-width="20%">
      <template slot-scope="scope">

        <el-button
            @click.native.prevent="userEdit(scope.$index,  scope.row)"
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
    let _this = this;
    /*
    _this.$axios({
      url:'/user/finduser?current=1&limilt=5',
      date: JSON.stringify({
        "creationDate": "2021-07-29 08:17:08.104",
        "lastUpdatedDate": "2021-08-29 08:17:08.104",
        "userAlias": "",
        "userName": "",
        "userStatus": ""
      }),
      header:{
        'Content-Type':'application/json',  //如果写成contentType会报错
      },
      method:"post"
    }).then(function (response){
      console.log(response)
      _this.tableData = response.data.info
    })
    */

    axios.post(
        '/user/finduser?current=1&limilt=5',
        {
          "creationDate": "2021-06-29 08:17:08.104",
          "lastUpdatedDate": "2021-08-30 08:17:08.104",
          "userAlias": "",
          "userName": "",
          "userStatus": ""
        }
    ).then(function (response){
      console.log(response)
      _this.tableData = response.data.info
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
        value: 'All',
        label: '全部'
      }, {
        value: 'Act',
        label: '有效'
      }, {
        value: 'Del',
        label: '无效'
      }],

      selectValue: ''

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