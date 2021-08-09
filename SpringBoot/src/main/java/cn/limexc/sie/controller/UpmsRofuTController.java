package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsRofuTQuery;
import cn.limexc.sie.entity.vo.UpmsRofuVo;
import cn.limexc.sie.service.UpmsRofuTService;
import cn.limexc.sie.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * UPMS权限管理 用户角色表 前端控制器
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Slf4j
@RestController
@RequestMapping("/sys/rofu")
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
    @PreAuthorize("hasAuthority('修改用户')")
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
    @PreAuthorize("hasAuthority('修改用户')")
    @DeleteMapping("/delrofu")
    public ResultData delRofu(@RequestParam("id") int id){
        boolean isdel =false;
        //通过传入的id，进行删除
        isdel = upmsRofuTService.removeById(id);
        return ResultData.success(isdel);
    }

    /**
     * 改 需要修改的字段...备注
     * @param rofu  rofu对象
     * @return      是否
     */
    @PreAuthorize("hasAuthority('修改用户')")
    @PostMapping("/editrofu")
    public ResultData editRofu(@RequestBody UpmsRofuT rofu){
        boolean isEdit = upmsRofuTService.updateById(rofu);
        return ResultData.success(isEdit);
    }

    /**
     * 该接口用于批量的进行用户角色的绑定与解绑操作.
     * 通过前端传入的角色id数组与用户id,查找数据库
     * 若数据已存在  ==>不操作
     * 若数据不存在  ==>添加
     * 若数据库多余  ==>删除
     * @return
     */
    @PreAuthorize("hasAuthority('修改用户')")
    @PostMapping("/editrofus")
    public ResultData editRofus(@RequestBody UpmsRofuVo userRoleVo){
        log.info(userRoleVo.toString());
        upmsRofuTService.updataByUidAndRoleList(userRoleVo);
        return ResultData.success(true);
    }



    /**
     * 通过用户id-->uid查询全部与之绑定的角色对象（非角色id）
     * 绑定的使用isSelect = true标识
     * @param uid  用户id
     * @return     格式化对象
     */
    @PreAuthorize("hasAnyAuthority('查找用户','新增用户','查找角色')")
    @GetMapping("/findrofu")
    public ResultData findUserRole(@RequestParam("uid") String uid){
        if ("undefined".equals(uid)){
            return  ResultData.success("");
        }
        List<UpmsRofuTQuery> upmsRoleTList = upmsRofuTService.listRofu(uid);
        for (UpmsRofuTQuery rofu:upmsRoleTList) {
            System.out.println(rofu.toString());
        }
        return ResultData.success(upmsRoleTList);
    }



}

