package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.exception.UserException;
import cn.limexc.sie.service.IndexService;
import cn.limexc.sie.service.UpmsMenuTService;
import cn.limexc.sie.service.UpmsRoleTService;
import cn.limexc.sie.service.UpmsUserTService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UpmsUserTService userService;

    @Autowired
    private UpmsRoleTService roleService;

    @Autowired
    private UpmsMenuTService menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户名获取用户登录信息
     *
     * @param userAlias
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String userAlias) {
        Map<String, Object> result = new HashMap<>();
        UpmsUserT user = userService.selectByUserAlias(userAlias);
        if (null == user) {
            throw new UserException();
        }

        //根据用户id获取角色
        List<UpmsRoleT> roleList = roleService.selectRoleByUserId(user.getUserId());
        //获取角色名称列表
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if(roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限
        List<String> permissionValueList = menuService.selectMenuValueByUserId(String.valueOf(user.getUserId()));
        redisTemplate.opsForValue().set(userAlias, permissionValueList);

        result.put("userAlias", user.getUserAlias());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名获取动态菜单
     * @param userAlias
     * @return
     */
    @Override
    public List<JSONObject> getMenu(String userAlias) {
        UpmsUserT user = userService.selectByUserAlias(userAlias);

        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = null;
                //menuService.selectPermissionByUserId(user.getUserId());
        return permissionList;
    }


}
