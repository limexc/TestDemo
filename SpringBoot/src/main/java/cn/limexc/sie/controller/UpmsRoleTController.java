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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * UPMS权限管理 角色信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/role")
public class UpmsRoleTController {

    @Autowired
    UpmsRoleTService upmsRoleTService;

    // 增
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
    @DeleteMapping(value = "/delrole")
    public ResultData delRole(@ApiParam(name = "id",value = "角色id",required = true)
                              @PathVariable String id){
        boolean isDelete = false;
        try {
            isDelete = upmsRoleTService.removeById(id);
            if (isDelete){
                return ResultData.success(isDelete);
            }else {
                return ResultData.fail(ResponseCode.ERROR.val(), "删除失败");
            }
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "删除失败");
        }
    }


    // 改
    @PostMapping("/deitrole")
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
    @GetMapping("/findroleid")
    public ResultData findRoleById(
            @ApiParam(name = "current",value = "页码",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @PathVariable int limit,
            @ApiParam(name = "upmsUserTQuery",value = "查询条件",required = false)
            @RequestBody(required = false) UpmsRoleTQuery upmsRoleTQuery){
        //创建page对象
        Page<UpmsRoleT> userTPage = new Page<UpmsRoleT>(current,limit);
        //构建条件
        QueryWrapper<UpmsRoleT> wrapper = new QueryWrapper<UpmsRoleT>();
        //多条件组合查询
        String roleName = upmsRoleTQuery.getRoleName();
        String roleStatus = upmsRoleTQuery.getRoleStatus();

        //判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(roleName)){
            wrapper.like("role_name",roleName);
        }
        if (!StringUtils.isEmpty(roleStatus)){
            wrapper.eq("role_status",roleStatus);
        }


        //调用方法实现条件查询分页
        upmsRoleTService.page(userTPage,wrapper);
        //总记录数
        long total = userTPage.getTotal();

        List<UpmsRoleT> records = userTPage.getRecords();
        return ResultData.success(records);

    }

    @GetMapping("/rolelist")
    public ResultData roleList(
            @ApiParam(name = "current",value = "页码",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @PathVariable int limit){
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

