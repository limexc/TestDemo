package cn.limexc.sie.service.impl;


import cn.limexc.sie.entity.*;
import cn.limexc.sie.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UpmsUserTService userTService;
    @Autowired
    private UpmsMenuTService menuTService;
    @Autowired
    private UpmsRofuTService rofuTService;
    @Autowired
    private UpmsRoahTService roahTService;
    @Autowired
    private UpmsRoleTService roleTService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        UpmsUserT user = userTService.selectByUserAlias(username);

        // 判断用户是否存在
        if (null == user){
            //throw new UsernameNotFoundException("用户名不存在！");
        }

        // 返回UserDetails实现类
        cn.limexc.sie.entity.UpmsUserT curUser = new cn.limexc.sie.entity.UpmsUserT();
        BeanUtils.copyProperties(user,curUser);

        //查询用户权限列表 需要使用 用户id查询 该用户的角色,通过角色来查询 权限
        //查询该用户的角色  可能有多个角色
        QueryWrapper<UpmsRofuT> queryWrapper = new QueryWrapper<UpmsRofuT>();
        queryWrapper.eq("ROFU_USERID",user.getUserId());
        List<UpmsRofuT> rofuList = rofuTService.list(queryWrapper);
        //获取角色的id列表
        List<Integer> roleIds = new ArrayList<>();
        for (UpmsRofuT roleuser:rofuList){
            roleIds.add(roleuser.getRofuRoleid());
        }
        //通过角色id查询 角色权限列表
        List<UpmsRoahT> roahList = roahTService.list(new QueryWrapper<UpmsRoahT>().in("ROAH_ROLEID",roleIds));
        //List<UpmsRoahT> roahList = roahTService.listByIds(roleIds);
        //通过id查询全部菜单  获得菜单id 并去重
        Set<String> menuSetIds = new HashSet<>();
        for (UpmsRoahT roahT:roahList){
            menuSetIds.add(String.valueOf(roahT.getRoahMenuid()));
        }
        List<String> authorities = new ArrayList<>();
        //这里应该放的是菜单名称
        //通过菜单id获得菜单名称
        List<UpmsMenuT> menuTList = menuTService.listByIds(menuSetIds);
        for (UpmsMenuT menu:menuTList){
            authorities.add(menu.getMenuName());
        }


        //通过角色id查询该用户的权限
        //List<UpmsMenuT> menuTList = menuTService.listByIds(ro);

        //List<String> authorities = menuTService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setMenuValueList(authorities);
        return securityUser;
    }



}
