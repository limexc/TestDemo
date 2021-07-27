package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsUserinfoT;
import cn.limexc.sie.service.UpmsUserinfoTService;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * UPMS权限管理系统用户信息表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/userinfo")
public class UpmsUserinfoTController {

    @Autowired
    private UpmsUserinfoTService upmsUserinfoTService;




    /**
     * 获取用户数据列表
     * @param pageNum 分页页号
     * @return        格式化后json数据
     */
    @GetMapping("/userList")
    public ResultData getAllUser(
            @RequestParam(value = "pageNum",defaultValue="1") int pageNum){
        List<UpmsUserinfoT> userinfoTS = null;
        try {
            //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
            PageHelper.startPage(pageNum,5);
            //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页
            userinfoTS=upmsUserinfoTService.list();
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数
            PageInfo pageInfo = new PageInfo(userinfoTS, 3);
            return ResultData.success(pageInfo);
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "读取数据失败");
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
    }

    /**
     * 删除用户 逻辑删除 将DeleteFlage置为Y
     * @param id      用户id
     * @return        返回格式化数据
     */
    @DeleteMapping("/{id}")
    public ResultData removeUser(@PathVariable String id){
        System.out.println(id);
        boolean isDelete = false;
        try {
            isDelete = upmsUserinfoTService.removeById(id);
            if (isDelete){
                return ResultData.success(isDelete);
            }else {
                return ResultData.fail(ResponseCode.ERROR.val(), "删除失败");
            }
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "删除失败");
        }
    }

}

