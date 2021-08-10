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
    <el-form ref="menuInfo" :model="menuInfo" :rules="rules" label-width="80px">
      <el-form-item label="菜单类型">
        <el-radio-group v-model="menuInfo.menuType" @change="">
          <el-radio label="目录" value="目录">目录</el-radio>
          <el-radio label="菜单" value="菜单">菜单</el-radio>
          <el-radio label="功能" value="功能">功能</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="菜单名称">
        <el-input  v-model="menuInfo.menuName" placeholder="菜单名称"></el-input>
      </el-form-item>

      <el-form-item label="上级菜单">
        <el-select v-model="menuInfo.menuUpper"  clearable placeholder="选择上级菜单">
          <el-option v-for="item in menuUppers"
                     :key="item.label"
                     :label="item.label"
                     :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="菜单标识">
        <el-input v-model="menuInfo.menuFlag" :disabled="disInput.flag" placeholder="菜单标识"></el-input>
      </el-form-item>
      <el-form-item label="菜单地址">
        <el-input  v-model="menuInfo.menuUrl" :disabled="disInput.url" placeholder="菜单地址"></el-input>
      </el-form-item>

      <el-form-item label="图标">
        <el-input  v-model="menuInfo.menuIco" placeholder="图标"></el-input>
      </el-form-item>
      <el-form-item label="排序号">
        <el-input  v-model="menuInfo.menuSerial" :disabled="disInput.serial" placeholder="排序号"></el-input>
      </el-form-item>

      <el-form-item label="状态">
        <el-radio-group v-model="menuInfo.menuStatus">
          <el-radio label="Act" value="Act">有效</el-radio>
          <el-radio label="Del" value="Del">无效</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="menuInfo.menuNote"></el-input>
      </el-form-item>
      <el-form-item style="text-align: right;">
        <el-button type="primary" @click="submitForm('menuInfo')">确定</el-button>
        <el-button @click="closeDialog(0)">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
  </el-dialog>
  </transition>
</template>

<script>
import menu from '@/api/upms/menu'

export default {
  name: 'ActivityWindow',
  components: {},
  data() {
    return {
      menuInfo:JSON.parse(JSON.stringify(this.itemInfo)),
      menuUppers:[],
      disInput:[{
        flag:false,
        url:false,
        serial:false
      }],
      showDialog: false,
      rules:{
        menuName:[
          { required: true, message: '请输入菜单名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }

    }

  },
  props: {
    dialogTitle: {
      type: String,
      default: "添加菜单",
    },
    itemInfo: {
      type: Object,
      default: function () {
        return {};
      },
    },
  },
  created() {
    this.getMenuUpper()
  },
  methods: {
    getMenuUpper(){
      menu.getUpperMenuList()
        .then(response=>{//请求成功
          this.menuUppers = response.data.info
          console.log(this.menuUppers)
        })
        .catch(error=>{//请求失败
          console.log(error)
        })
    },

    // 保存操作
    submitForm(formName) {
      const that = this;
      const params = Object.assign(this.menuInfo, {});
      console.log(params)
      if (params.menuId!==undefined){
        console.log("走修改")
        menu.editMenuInfo(params).then(
          that.$message({
            message: "操作成功！",
            type: "success",
          })
        )
        that.closeDialog(1);
      }else {
        console.log("走新增")
        menu.addMenuInfo(params).then(
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
      this.$refs["menuInfo"].resetFields();
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
