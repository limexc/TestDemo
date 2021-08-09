package cn.limexc.sie.controller;


import cn.hutool.crypto.SecureUtil;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsRoleTQuery;
import cn.limexc.sie.entity.vo.UpmsUserTQuery;
import cn.limexc.sie.service.UpmsRoleTService;
import cn.limexc.sie.service.UpmsUserTService;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * UPMS权限管理 角色信息表 前端控制器
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/sys/role")
public class UpmsRoleTController {

    @Autowired
    UpmsRoleTService upmsRoleTService;

    // 增
    @PreAuthorize("hasAuthority('新增角色')")
    @PostMapping(value = "/addrole")
    public ResultData addRole(
            @ApiParam(name = "upmsRoleT",value = "角色信息",required = true)
            @RequestBody UpmsRoleT upmsRoleT){
        boolean save = false;
        try {
            //用来判断传入的数据是否符合要求
            if (upmsRoleT!=null){

            }
            save = upmsRoleTService.save(upmsRoleT);
            if (save){
                return ResultData.success(save);
            }
            return ResultData.fail(ResponseCode.ERROR.val(), "存储失败");
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "数据库错误");
        }
    }



    // 删
    @PreAuthorize("hasAuthority('删除角色')")
    @DeleteMapping(value = "/delroles")
    public ResultData delRoles(@ApiParam(name = "ids",value = "角色id",required = true)
                                  @RequestBody List<String> ids){

        for (int i=0;i<ids.size();i++){
            upmsRoleTService.removeById(ids.get(i));
        }
        return ResultData.success(true);

    }

    // 删
    @PreAuthorize("hasAuthority('删除角色')")
    @DeleteMapping(value = "/delrole")
    public ResultData delRole(@ApiParam(name = "id",value = "角色id",required = true)
                              @RequestParam("id") String id){

        upmsRoleTService.removeById(id);

        return ResultData.success(true);

    }

    // 改
    @PreAuthorize("hasAuthority('修改角色')")
    @PostMapping("/editrole")
    public ResultData editRole(
            @RequestBody UpmsRoleT upmsRoleT){
        boolean change = false;
        try{
            //用来判断传入的数据是否符合要求  后面单独抽取
            if (upmsRoleT!=null){



            }
            change = upmsRoleTService.updateById(upmsRoleT);
            if (change){
                return ResultData.success(change);
            }
            return ResultData.fail(ResponseCode.ERROR.val(), "信息修改失败");
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ResponseCode.ERROR.val(), "数据库错误");
        }
    }


    // 查
    @PreAuthorize("hasAuthority('查询角色')")
    @PostMapping("/findrole")
    public ResultData findRole(
            @ApiParam(name = "current",value = "页码",required = true)
            @RequestParam(value = "current",required = false,defaultValue = "1") int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @RequestParam(value = "limit",required = false,defaultValue = "5") int limit,
            @ApiParam(name = "upmsUserTQuery",value = "查询条件")
            @RequestBody(required = false) UpmsRoleTQuery upmsRoleTQuery){
        //创建page对象
        Page<UpmsRoleT> userTPage = new Page<UpmsRoleT>(current,limit);
        //构建条件
        QueryWrapper<UpmsRoleT> wrapper = new QueryWrapper<UpmsRoleT>();
        //多条件组合查询
        if (upmsRoleTQuery!=null){
            String roleName = upmsRoleTQuery.getRoleName();
            String roleStatus = upmsRoleTQuery.getRoleStatus();
            int roleId = upmsRoleTQuery.getRoleId();

            //判断条件是否为空，如果不为空则拼接条件
            if (!StringUtils.isEmpty(roleName)){
                wrapper.like("role_name",roleName);
            }
            if (!StringUtils.isEmpty(roleStatus)){
                wrapper.eq("role_status",roleStatus);
            }
            if (roleId!=0){
                wrapper.eq("role_id",roleId);
            }
        }

        //调用方法实现条件查询分页
        upmsRoleTService.page(userTPage,wrapper);
        //总记录数
        long total = userTPage.getTotal();

        List<UpmsRoleT> records = userTPage.getRecords();
        return ResultData.success(records,String.valueOf(total));

    }
    @PreAuthorize("hasAuthority('查询角色')")
    @GetMapping("/rolelist")
    public ResultData roleList(
            @ApiParam(name = "current",value = "页码",required = true)
            @RequestParam(value = "current",required = false) int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @RequestParam(value = "limit",required = false) int  limit){
        //创建page对象
        Page<UpmsRoleT> userTPage = new Page<UpmsRoleT>(current,limit);

        //调用方法实现条件分页
        upmsRoleTService.page(userTPage);
        //总记录数
        long total = userTPage.getTotal();

        List<UpmsRoleT> records = userTPage.getRecords();
        return ResultData.success(records);

    }

}

