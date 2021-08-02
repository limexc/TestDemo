package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsAuthT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.service.UpmsAuthTService;
import cn.limexc.sie.util.ResultData;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * UPMS权限管理需要 权限表 前端控制器
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/auth")
public class UpmsAuthTController {

    @Autowired
    private UpmsAuthTService upmsAuthTService;

    // 增 当增加目录、菜单、功能时自动增加该字段？  通过数据库实现？
    // 该功能通过数据库触发器实现

    // 删改
    //该功能同步MENU表，通过数据库触发器实现，对数据的修改（逻辑删除）


    // 查 应该返回表中所有权限
    @GetMapping("/findallauth")
    public ResultData findAllAuth(){
        List<UpmsAuthT> authTS=upmsAuthTService.list();
        return ResultData.success(authTS);
    }




}

