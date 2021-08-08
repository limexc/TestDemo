package cn.limexc.sie.controller;


import cn.limexc.sie.entity.vo.UpmsRoahTVo;
import cn.limexc.sie.service.UpmsRoahTService;
import cn.limexc.sie.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * UPMS角色权限
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/sys/roah")
public class UpmsRoahTController {

    @Autowired
    private UpmsRoahTService roahTService;

    /**
     * 增 当用户为角色增加权限时调用
     * 传入的应该是前端提交的 角色权限列表
     * 角色ID -- 权限1 权限2 需要单独写sql <foreach>循环
     * @param roahTVO    角色id 与 权限Id List
     * @return          添加状态
     */
    @PostMapping("/addroah")
    public ResultData addRoah(
            @RequestBody UpmsRoahTVo roahTVO){

        boolean isSave = roahTService.addRoleAuth(roahTVO);

        return ResultData.success(true);
    }

    /**
     * 改 某一角色中的某一或一部分权限
     * 传入 修改后的 角色权限对象进行更新
     *         //要包含从某个角色中删除权限，即前端用户取消勾选某一权限。
     *         //如果之前已经存在不修改，若不存在就插入，若List中不存在就删除
     *         //获得当前角色已经勾选的权限，对比list
     *         //在提交时判断是插入还是删除？应该可以和新增进行合并，使用该接口即可。
     * @param roahTVO  角色id 与 权限Id List
     * @return         状态
     */
    @PostMapping("/editroah")
    public ResultData editRoah(@RequestBody UpmsRoahTVo roahTVO){
        boolean isEdit = roahTService.editRoleAuth(roahTVO);
        return ResultData.success(isEdit);
    }


    public ResultData findAllRoah(String rid){
        //获取全部权限
        //获取全部角色已经选择的权限，将已经勾选的进行特殊标识

        return ResultData.success(true);
    }






}

