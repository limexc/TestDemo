package cn.limexc.sie.controller;


import cn.hutool.crypto.SecureUtil;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsUserTQuery;
import cn.limexc.sie.service.UpmsUserTService;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @author testjava
 * @since 2021-07-27
 */
@Api("用户管理")
@RestController
@RequestMapping("/userinfo")
public class UpmsUserTController {

    @Autowired
    private UpmsUserTService upmsUserTService;


    /**
     * 获取用户数据列表
     * @param current 页码
     * @param limit   分页大小
     * @return        格式化后json数据
     */
    @ApiOperation("用户列表")
    @GetMapping("/userList")
    public ResultData getAllUser(
            @ApiParam(name = "current",value = "页码",required = false)
            @RequestParam(value = "current",defaultValue="1") int current,
            @ApiParam(name = "limit",value = "分页大小",required = false)
            @RequestParam(value = "limit",defaultValue = "5") int limit
            ){
        List<UpmsUserT> userTS = null;
        try {
            //这里还是用的PageHelper，注意修改
            //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
            PageHelper.startPage(current,limit);
            //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页
            userTS=upmsUserTService.list();
            //3.使用PageInfo包装查询后的结果
            PageInfo pageInfo = new PageInfo(userTS, limit);
            return ResultData.success(pageInfo);
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "读取数据失败");
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
    }

    @ApiOperation("用户分页多条件查询")
    @PostMapping("/pageUserCondition/{current}/{limit}")
    public ResultData pageUserCondition(
            @ApiParam(name = "current",value = "页码",required = true)
            @PathVariable int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @PathVariable int limit,
            @ApiParam(name = "upmsUserTQuery",value = "查询条件",required = false)
            @RequestBody(required = false)UpmsUserTQuery upmsUserTQuery){
        //创建page对象
        Page<UpmsUserT> userTPage = new Page<UpmsUserT>(current,limit);
        //构建条件
        QueryWrapper<UpmsUserT> wrapper = new QueryWrapper<UpmsUserT>();
        //多条件组合查询
        String userAlias = upmsUserTQuery.getUserAlias();
        String userName=upmsUserTQuery.getUserName();
        String userStatus=upmsUserTQuery.getUserStatus();
        Date creationDate=upmsUserTQuery.getCreationDate();
        Date lastUpdatedDate=upmsUserTQuery.getLastUpdatedDate();
        //判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(userAlias)){
            wrapper.like("user_alias",userAlias);
        }
        if (!StringUtils.isEmpty(userName)){
            wrapper.eq("user_name",userName);
        }
        if (!StringUtils.isEmpty(userStatus)){
            wrapper.eq("user_status",userStatus);
        }
        if (!StringUtils.isEmpty(creationDate)){
            wrapper.ge("creation_date",creationDate);
        }
        if (!StringUtils.isEmpty(lastUpdatedDate)){
            wrapper.le("last_updated_date",lastUpdatedDate);
        }

        //调用方法实现条件查询分页
        upmsUserTService.page(userTPage,wrapper);
        //总记录数
        long total = userTPage.getTotal();

        List<UpmsUserT> records = userTPage.getRecords();
        return ResultData.success(records);
    }

    /**
     * 添加用户功能
     * @param upmsUserT  用户
     * @return               格式化数据
     */
    @ApiOperation("添加用户")
    @PostMapping("/addUser")
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
    @GetMapping("/getuser/{id}")
    public ResultData getUser(@PathVariable String id){
        UpmsUserT user = null;
        user=upmsUserTService.getById(id);
        if (user!=null){
            return ResultData.success(user);
        }
        return ResultData.fail(ResponseCode.ERROR.val(), "未查询到该用户信息");
    }

    @PostMapping("/updatauser")
    public ResultData editUser(@RequestBody UpmsUserT upmsUserT){
        boolean change = false;
        try{
            //用来判断传入的数据是否符合要求  后面单独抽取
            if (upmsUserT!=null){
                if (upmsUserT.getUserPasswd()!=""){
                    upmsUserT.setUserPasswd(
                            //用户密码加密存储
                            SecureUtil.md5(upmsUserT.getUserPasswd())
                    );
                }


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
     * @param id      用户id
     * @return        返回格式化数据
     */
    @ApiOperation("删除用户 逻辑删除")
    @DeleteMapping("/{id}")
    public ResultData removeUser(
            @ApiParam(name = "id",value = "用户id",required = true)
            @PathVariable String id){
        System.out.println(id);
        boolean isDelete = false;
        try {
            isDelete = upmsUserTService.removeById(id);
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

