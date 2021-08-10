import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '../views/layout/Layout'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  //{ path: '/404', component: () => import('@/views/404'), hidden: true },


  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '首页', icon: 'tree' }
      }]
  },

  {
    path: '/系统管理',
    component: Layout,
    redirect: '/system',
    name: '系统管理',
    meta: { title: '系统管理', icon: 'form' },
    children: [
      {
        path: 'user',
        name: '用户管理',
        component: () => import('@/views/user/UserManage'),
        meta: { title: '用户管理', icon: 'group' }
      },
      {
        path: 'role',
        name: '角色管理',
        component: () => import('@/views/role/RoleManage'),
        meta: { title: '角色管理', icon: 'tree' }
      },
      {
        path: 'menu',
        name: '菜单管理',
        component: () => import('@/views/menu/MenuManage'),
        meta: { title: '菜单管理', icon: 'menu' }
      }
    ]
  },
/**
  {
    path: '/测试',
    component: Layout,
    redirect: '/测试',
    name: '测试',
    meta: { title: '测试', icon: 'example' },
    children: [
      {
        path: 'delog',
        name: '弹出',
        component: () => import('@/views/test/index'),
        meta: { title: '弹出框', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },
**/
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
