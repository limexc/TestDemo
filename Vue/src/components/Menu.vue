<template>
  <el-submenu v-if="menu.children && menu.children.length >= 1" :index="'' + menu.id">
    <template slot="title">
      <i :class="menu.icon" ></i>
      <span slot="title">{{menu.name}}</span>
    </template>
    <MenuTree v-for="item in menu.children" :key="item.id" :menu="item"></MenuTree>
  </el-submenu>
  <el-menu-item v-else :index="(menu.href?menu.href:menu.id)">
    <i :class="menu.icon"></i>
    <span slot="title">{{menu.name}}</span>
  </el-menu-item>
</template>

<script>
export default {
  name: 'v-menu',
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  data(){
    return{
      menu: [
        {
        "id": 1,
        "path": "/admin",
        "name": "AdminIndex",
        "nameZh": "首页",
        "iconCls": "el-icon-s-home",
        "component": "AdminIndex",
        "parentId": 0,
        "children": null
      },
        {
          "id": 2,
          "path": "/admin",
          "name": "User",
          "nameZh": "用户管理",
          "iconCls": "el-icon-user",
          "component": "AdminIndex",
          "parentId": 0,
          "children": [{
            "id": 6,
            "path": "/admin/user/profile",
            "name": "Profile",
            "nameZh": "用户信息",
            "iconCls": null,
            "component": "user/UserProfile",
            "parentId": 2,
            "children": null
          },
            {
              "id": 7,
              "path": "/admin/user/role",
              "name": "Role",
              "nameZh": "角色配置",
              "iconCls": null,
              "component": "user/Role",
              "parentId": 2,
              "children": null
            }
          ]
        },
        {
          "id": 3,
          "path": "/admin",
          "name": "Content",
          "nameZh": "内容管理",
          "iconCls": "el-icon-tickets",
          "component": "AdminIndex",
          "parentId": 0,
          "children": [{
            "id": 9,
            "path": "/admin/content/department",
            "name": "Department",
            "nameZh": "就业部类",
            "iconCls": null,
            "component": "content/department",
            "parentId": 3,
            "children": null
          },
            {
              "id": 10,
              "path": "/admin/content/student",
              "name": "Student",
              "nameZh": "学生类",
              "iconCls": null,
              "component": "content/student",
              "parentId": 3,
              "children": [{
                "id": 20,
                "path": "/admin/content/student/graduateInfo",
                "name": "graduateInfo",
                "nameZh": "毕业生信息",
                "iconCls": null,
                "component": "content/student/graduateInfo",
                "parentId": 10,
                "children": null
              }]
            },
            {
              "id": 11,
              "path": "/admin/content/enterprise",
              "name": "Enterprise",
              "nameZh": "企业类",
              "iconCls": null,
              "component": "content/enterprise",
              "parentId": 3,
              "children": null
            }
          ]
        },
        {
          "id": 4,
          "path": "/admin",
          "name": "System",
          "nameZh": "系统管理",
          "iconCls": "el-icon-s-tools",
          "component": "AdminIndex",
          "parentId": 0,
          "children": [{
            "id": 12,
            "path": "/admin/system/run",
            "name": "Run",
            "nameZh": "运行情况",
            "iconCls": null,
            "component": "system/run",
            "parentId": 4,
            "children": null
          },
            {
              "id": 13,
              "path": "/admin/system/data",
              "name": "Data",
              "nameZh": "备份恢复数据库",
              "iconCls": null,
              "component": "system/data",
              "parentId": 4,
              "children": null
            },
            {
              "id": 14,
              "path": "/admin/system/log",
              "name": "Log",
              "nameZh": "操作日志",
              "iconCls": null,
              "component": "system/log",
              "parentId": 4,
              "children": null
            }
          ]
        },
        {
          "id": 5,
          "path": "/admin",
          "name": "Link",
          "nameZh": "链接",
          "iconCls": null,
          "component": "AdminIndex",
          "parentId": 0,
          "children": null
        }
      ]
    };
  },
  methods:{
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>