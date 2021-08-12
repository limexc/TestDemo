package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.entity.UpmsRoahT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.mapper.UpmsMenuTMapper;
import cn.limexc.sie.service.UpmsMenuTService;
import cn.limexc.sie.service.UpmsRoahTService;
import cn.limexc.sie.service.UpmsRoleTService;
import cn.limexc.sie.service.UpmsUserTService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * UPMS权限管理 菜单表 服务实现类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Service
@Slf4j
public class UpmsMenuTServiceImpl extends ServiceImpl<UpmsMenuTMapper, UpmsMenuT> implements UpmsMenuTService {
    /**
     *
     */
    @Autowired
    private UpmsMenuTMapper menuTMapper;
    @Autowired
    private UpmsUserTService userTService;
    @Autowired
    private UpmsRoleTService roleTService;
    @Autowired
    private UpmsRoahTService roahTService;


    @Override
    public List<UpmsMenuT> queryAllMenu(){
        QueryWrapper<UpmsMenuT> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("menu_id");
        List<UpmsMenuT> menuList = baseMapper.selectList(wrapper);

        List<UpmsMenuT> resultList = buildMenu(menuList);

        return resultList;
    }

    private List<UpmsMenuT> buildMenu(List<UpmsMenuT> menuList) {
        //遍历list, upper为0的 设置level 1
        List<UpmsMenuT> finalNode = new ArrayList<>();
        for (UpmsMenuT menuNode: menuList){
            if (menuNode.getMenuUpper()==0){
                menuNode.setLevel(1);
                finalNode.add(selectChildren(menuNode,menuList));
            }
        }
        return finalNode;
    }

    private UpmsMenuT selectChildren(UpmsMenuT menuNode, List<UpmsMenuT> menuList) {
        menuNode.setChildren(new ArrayList<UpmsMenuT>());
        for (UpmsMenuT it : menuList){
            if (menuNode.getMenuId().equals(it.getMenuUpper())){
                int level = menuNode.getLevel()+1;
                it.setLevel(level);
                //如果Children为空,初始化
                if (menuNode.getChildren()==null){
                    menuNode.setChildren(new ArrayList<UpmsMenuT>());
                }
                //将查询出来的子菜单放到父菜单中
                menuNode.getChildren().add(selectChildren(it,menuList));
            }
        }
        return menuNode;

    }
    //=====================================================================\\


    @Override
    public List<String> selectMenuValueByUserAlias(String userAlias) {
        List<String> selectPermissionValueList = new ArrayList<>();
        selectPermissionValueList = getMenuValue("",userAlias);
        return selectPermissionValueList;
    }

    //根据用户id获取用户菜单
    @Override
    public List<String> selectMenuValueByUserId(String id) {

        List<String> selectPermissionValueList = new ArrayList<>();
        selectPermissionValueList = getMenuValue(id,"");
        return selectPermissionValueList;
    }

    private List<String> getMenuValue(String id, String userAlias) {
        List<String> selectPermissionValueList = new ArrayList<>();
        //判断传入的值是什么
        if (!"".equals(userAlias)) {
            //通过userAlias查id
            UpmsUserT userT = userTService.selectByUserAlias(userAlias);
            id=String.valueOf(userT.getUserId());
        }

        if (this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限  即所有菜单名称
            List<UpmsMenuT> menuTList = baseMapper.selectList(new QueryWrapper<UpmsMenuT>().select("MENU_NAME"));
            for (UpmsMenuT menuT : menuTList) {
                selectPermissionValueList.add(menuT.getMenuName());
            }
        } else {
            //根据用户id获得 其拥有的角色列表
            List<UpmsRoleT> roleTList = roleTService.selectRoleByUserId(Integer.parseInt(id));

            //通过角色查询全部包含的 权限(菜单)名称 使用set去重
            //获取角色的id列表
            List<Integer> roleIds = new ArrayList<>();
            if (roleTList.size() > 0) {
                for (UpmsRoleT roleT : roleTList) {
                    roleIds.add(roleT.getRoleId());
                }
            }
            //通过角色id查询 角色权限列表
            List<UpmsRoahT> roahList = roahTService.list(new QueryWrapper<UpmsRoahT>().in("ROAH_ROLEID", roleIds));
            //List<UpmsRoahT> roahList = roahTService.listByIds(roleIds);
            //通过id查询全部菜单
            Set<String> menuSetIds = new HashSet<>();
            for (UpmsRoahT roahT : roahList) {
                menuSetIds.add(String.valueOf(roahT.getRoahMenuid()));
            }
            List<UpmsMenuT> menuTList = menuTMapper.selectBatchIds(menuSetIds);

            if (menuTList.size() > 0) {
                for (int i = 0; i < menuTList.size(); i++) {
                    //通过判断字段内容
                    if ("Act".equals(menuTList.get(i).getMenuStatus())){
                        log.info("该用户可以访问的菜单名称:{}",menuTList.get(i).getMenuName());
                        selectPermissionValueList.add(menuTList.get(i).getMenuName());
                    }
                }
            }

        }

        return selectPermissionValueList;
    }

    //未完成
    @Override
    public List<JSONObject> selectMenuByUserId(Integer userId) {
        return null;
    }

    //@Override
    //public List<JSONObject> selectPermissionByUserId(String userId) {
        //List<UpmsMenuT> selectPermissionList = null;
        //if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            //selectPermissionList = baseMapper.selectList(null);
       // } else {
            //selectPermissionList = (userId);
       // }

        //List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
        //List<JSONObject> result = MemuHelper.bulid(permissionList);
        //return result;
   // }

    /**
     * 判断用户是否系统管理员
     * @param userId 用户id
     * @return       boolean
     */
    private boolean isSysAdmin(String userId) {
        UpmsUserT user = userTService.getById(userId);

        if(null != user && "admin".equals(user.getUserAlias())) {
            return true;
        }
        return false;
    }


}
