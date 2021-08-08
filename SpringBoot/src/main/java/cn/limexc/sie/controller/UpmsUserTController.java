package cn.limexc.sie.controller;


import cn.hutool.crypto.SecureUtil;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsUserTQuery;
import cn.limexc.sie.service.UpmsUserTService;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * UPMS权限管理系统用户信息表 前端控制器
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Api("用户管理")
@RestController
@RequestMapping("/sys/user")
public class UpmsUserTController {

    @Autowired
    private UpmsUserTService upmsUserTService;


    /**
     * 获取用户数据列表
     * @param current 页码
     * @param limit   分页大小
     * @return        格式化后json数据
     */
    @ApiOperation("用户分页多条件查询")
    @PostMapping("/finduser")
    public ResultData pageUserCondition(
            @ApiParam(name = "current",value = "页码",required = true)
            @RequestParam(value = "current",defaultValue = "1")  int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @RequestParam(value = "limit",defaultValue = "5")  int limit,
            @ApiParam(name = "upmsUserTQuery",value = "查询条件",required = false)
            @RequestBody(required = true)UpmsUserTQuery upmsUserTQuery){
        System.out.println("current: "+current+" limit: "+limit);
        //创建page对象
        Page<UpmsUserT> userTPage = new Page<UpmsUserT>(current,limit);
        //构建条件
        QueryWrapper<UpmsUserT> wrapper = new QueryWrapper<UpmsUserT>();
        //多条件组合查询
        String userAlias = upmsUserTQuery.getUserAlias();
        String userName = upmsUserTQuery.getUserName();
        String userStatus = upmsUserTQuery.getUserStatus();
        Date beginDate = upmsUserTQuery.getBeginDate();
        Date endDate = upmsUserTQuery.getEndDate();
        //判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(userAlias)){
            wrapper.like("user_alias",userAlias);
        }
        if (!StringUtils.isEmpty(userName)){
            wrapper.like("user_name",userName);
        }
        if (!StringUtils.isEmpty(userStatus)){
            wrapper.eq("user_status",userStatus);
        }
        if (!StringUtils.isEmpty(beginDate)){
            wrapper.ge("creation_date",beginDate);
        }
        if (!StringUtils.isEmpty(endDate)){
            wrapper.le("creation_date",endDate);
        }

        //调用方法实现条件查询分页
        upmsUserTService.page(userTPage,wrapper);
        //总记录数
        String total = String.valueOf(userTPage.getTotal());

        List<UpmsUserT> records = userTPage.getRecords();
        //密码不回传  这是查询的时候没搞好
        for (int i=0;i<records.size();i++){
            records.set(i, records.get(i).setUserPasswd(""));
        }
        return ResultData.success(records,total);
    }

    /**
     * 添加用户功能
     * @param upmsUserT  用户
     * @return               格式化数据
     */
    @ApiOperation("添加用户")
    @PostMapping("/adduser")
    public ResultData addUser(
            @ApiParam(name = "UpmsUserT",value = "用户信息",required = true)
            @RequestBody UpmsUserT upmsUserT){

        boolean save = false;
        try {
            //用来判断传入的数据是否符合要求
            if (upmsUserT!=null){

                if (upmsUserT.getUserPasswd()!=""){
                    upmsUserT.setUserPasswd(
                            //用户密码加密存储
                            SecureUtil.md5(upmsUserT.getUserPasswd())
                    );
                }


            }
            save = upmsUserTService.save(upmsUserT);
            if (save){
                return ResultData.success(save);
            }
            return ResultData.fail(ResponseCode.ERROR.val(), "存储失败");
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "数据库错误");
        }

    }


    /**
     * 根据用户id查询用户信息，用于在修改用户信息界面展示等
     * @param id  用户id
     * @return    格式化数据信息
     */
    @GetMapping("/getuser")
    public ResultData getUser(@RequestParam("id") String id){
        UpmsUserT user = null;
        user=upmsUserTService.getById(id);
        if (user!=null){
            return ResultData.success(user);
        }
        return ResultData.fail(ResponseCode.ERROR.val(), "未查询到该用户信息");
    }

    @PostMapping("/edituser")
    public ResultData editUser(@RequestBody UpmsUserT upmsUserT){
        boolean change = false;
        UpmsUserT user = null;
        user=upmsUserTService.getById(upmsUserT.getUserId());
        try{
            //用来判断传入的数据是否符合要求  后面单独抽取
            if (upmsUserT!=null){
                if (upmsUserT.getUserPasswd()!=""){
                    upmsUserT.setUserPasswd(
                        //用户密码加密存储
                        SecureUtil.md5(upmsUserT.getUserPasswd())
                    );
                }else {
                    upmsUserT.setUserPasswd(user.getUserPasswd());
                }
                upmsUserT.setLastUpdatedDate(null);



            }
            change = upmsUserTService.updateById(upmsUserT);
            if (change){
                return ResultData.success(change);
            }
            return ResultData.fail(ResponseCode.ERROR.val(), "用户信息修改失败");
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ResponseCode.ERROR.val(), "数据库错误");
        }
    }


    /**
     * 删除用户 逻辑删除 将DeleteFlage置为Y
     * @param ids      用户id
     * @return        返回格式化数据
     */
    @ApiOperation("删除用户 逻辑删除")
    @DeleteMapping("/deluser")
    public ResultData removeUser(
            @ApiParam(name = "ids",value = "用户ids",required = true)
            @RequestBody List<String> ids){
        System.out.println(ids.toString());

        try {
            for (int i=0;i<ids.size();i++){
                upmsUserTService.removeById(ids.get(i));
            }
            return ResultData.success(true);
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "删除失败");
        }
    }

}

