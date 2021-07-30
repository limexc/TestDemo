package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsRofuTQuery;
import cn.limexc.sie.service.UpmsRofuTService;
import cn.limexc.sie.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * UPMS权限管理 用户角色表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/rofu")
public class UpmsRofuTController {

    @Autowired
    UpmsRofuTService upmsRofuTService;

    //

    /**
     *  增  数据库中增加一条字段  用户名与角色ID绑定（原型页面中使用用户名绑定） 数据库使用id
     *
     * @param upmsRofuT 传入包含uid与rid的对象
     * @return  格式化后数据
     */
    @PostMapping("/addrofu")
    public ResultData addRofu(@RequestBody UpmsRofuT upmsRofuT){
        boolean isAdd =false;
        //判断输入的用户id与角色id是否都存在 --- 使用用户名进行绑定的话可以使用条件查询
        //但是在用户后面点击设置对象还用在单独输入一次用户名吗？
        isAdd = upmsRofuTService.addRofu(upmsRofuT);
        return ResultData.success(isAdd);
    }


    /**
     * 删 页面取消勾选解除绑定
     * @param id  rofu中的id属性
     * @return    格式化数据
     */
    @DeleteMapping("/delrofu")
    public ResultData delRofu(@RequestParam("id") int id){
        boolean isdel =false;
        //通过传入的id，进行删除
        isdel = upmsRofuTService.removeById(id);
        return ResultData.success(isdel);
    }

    // 改 貌似没有需要修改的字段...备注？


    // 查 设置用户角色页面用于展示勾选了那些角色 不需要分页
    //@PostMapping("/rofulist")
    public ResultData rofuList(@RequestBody UpmsUserT userT){
        //查两次，第一次查用户勾选了那些角色，第二次查全部的角色，创建返回实体进行标记返回格式数据。  想法1
        /**
         * 暂未完成
         */
        List<UpmsRofuTQuery> rofulist =upmsRofuTService.listRofu(userT);
        return ResultData.success(rofulist);
    }

    /**
     * | 也可以添加通过id查询单个 用于查看备注？
     * @param id
     * @return
     */
    @PostMapping("/findrofu")
    public ResultData findRofu(@RequestParam("id") int id){

        UpmsRofuT upmsRofuT = upmsRofuTService.getById(id);

        return ResultData.success(upmsRofuT);
    }

    /**
     * 通过用户id-->uid查询全部与之绑定的角色对象（非角色id）
     * @param uid  用户id
     * @return     格式化对象
     */
    public ResultData findUserRole(@RequestParam("uid") int uid){

        List<UpmsRoleT> upmsRoleTList = upmsRofuTService.listRole(uid);


        return ResultData.success(upmsRoleTList);
    }



}

