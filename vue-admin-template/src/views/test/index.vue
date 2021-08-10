<template>
  <div class="dialog-demo">
    <el-divider content-position="left">弹框组件v-if的应用demo</el-divider>
    <p class="instructions">
      el-dialog弹框的visible.sync设置显示和隐藏往往带来很多额外的工作：处理数据的变更，带校验表单的内容移除和校验清除等
    </p>
    <p class="instructions">
      需要我们在各种隐藏的bug里耗费很多精力，处理难度会随着表单内容复杂程度的增加不断提高。（实际业务场景中经常出现非常复杂的表单页面）
    </p>
    <p class="instructions">
      使用v-if既保证数据干净每次都是新弹框，又保留弹框弹出效果让整个调用逻辑非常简单
    </p>
    <div>
      <ul class="desc-list">
        <li>不用担心不同数据调用弹框造成的数据污染和页面bug</li>
        <li>保留了Element提供的el-dialog弹框弹出特效</li>
        <li>模拟了完整的操作流程</li>
      </ul>
    </div>
    <el-button type="primary" @click="addItem">添加一项</el-button>
    <el-table
      v-loading="tableLoading"
      :data="tableData"
      style="width: 80%; margin-top: 20px;"
    >
      <el-table-column prop="date" label="日期"> </el-table-column>
      <el-table-column prop="name" label="姓名"> </el-table-column>
      <el-table-column prop="province" label="省份"> </el-table-column>
      <el-table-column prop="city" label="市区"> </el-table-column>
      <el-table-column prop="address" label="地址" width="300">
      </el-table-column>
      <el-table-column prop="zip" label="邮编"> </el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click.native.prevent="editRow(scope.row)"
          >
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--弹框组件开始-----------------------start-->
    <dialog-component
      v-if="showDialog"
      ref="dialogComponent"
      :dialog-title="dialogTitle"
      :item-info="tableItem"
      @closeDialog="closeDialog"
    ></dialog-component>
    <!--弹框组件开始-----------------------end-->
  </div>
</template>

<script>
import DialogComponent from "@/components/Test/dialogComponent"
export default {
  name: "DialogDemo",
  components: { DialogComponent },
  data() {
    return {
      tableLoading: false,
      showDialog: false,
      dialogTitle: "添加人员",
      tableData: [],
      tableItem: {
        id: "",
        date: "",
        name: "",
        province: "",
        city: "",
        address: "",
        zip: "",
      },
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    // 获取表格数据
    fetchData() {
      const that = this;
      that.tableLoading = true;
      that.tableData = [
        {
          id: 0,
          date: "2016-05-03",
          name: "张三",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          id: 1,
          date: "2016-05-02",
          name: "李四",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          id: 2,
          date: "2016-05-04",
          name: "王五",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          id: 3,
          date: "2016-05-01",
          name: "法外狂徒",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          id: 4,
          date: "2016-05-08",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          id: 5,
          date: "2016-05-06",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
        {
          id: 6,
          date: "2016-05-07",
          name: "王小虎",
          province: "上海",
          city: "普陀区",
          address: "上海市普陀区金沙江路 1518 弄",
          zip: 200333,
        },
      ];
      setTimeout(() => {
        that.tableLoading = false;
      }, 1500);
    },
    // 添加操作
    addItem() {
      this.tableItem = {
        id: "",
        date: "",
        name: "",
        province: "",
        city: "",
        address: "",
        zip: "",
      };
      this.dialogTitle = "添加人员";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["dialogComponent"].showDialog = true;
      });
    },
    // 编辑操作
    editRow(row) {
      this.tableItem = row;
      this.dialogTitle = "编辑人员";
      this.showDialog = true;
      this.$nextTick(() => {
        this.$refs["dialogComponent"].showDialog = true;
      });
    },
    // 关闭操作
    closeDialog(flag) {
      if (flag) {
        // 重新刷新表格内容
        this.fetchData();
      }
      this.showDialog = false;
    },
  },
};
</script>

<style scoped lang="scss">
.dialog-demo {
  padding: 20px;
  .instructions {
    font-size: 14px;
    padding: 10px 0;
    color: #304455;
  }
  .desc-list {
    padding: 10px 0 30px 40px;
    line-height: 30px;
    font-size: 14px;
    color: #606266;
    list-style-type: disc;
  }
}
</style>
