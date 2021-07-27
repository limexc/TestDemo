package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsUserinfoT;
import cn.limexc.sie.entity.vo.UpmsUserinfoTQuery;
import cn.limexc.sie.service.UpmsUserinfoTService;
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
public class UpmsUserinfoTController {

    @Autowired
    private UpmsUserinfoTService upmsUserinfoTService;


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
            @RequestParam(value = "limit",defaultValue = "5") int limit
            ){
        List<UpmsUserinfoT> userinfoTS = null;
        try {
            //这里还是用的PageHelper，注意修改
            //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
            PageHelper.startPage(current,5);
            //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页
            userinfoTS=upmsUserinfoTService.list();
            //3.使用PageInfo包装查询后的结果,3是连续显示的条数
            PageInfo pageInfo = new PageInfo(userinfoTS, limit);
            return ResultData.success(pageInfo);
        }catch (Exception e){
            return ResultData.fail(ResponseCode.ERROR.val(), "读取数据失败");
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
    }

    @PostMapping("/pageUserCondition/{current}/{limit}")
    public ResultData pageUserCondition(@PathVariable int current,@PathVariable int limit,
                                        @RequestBody(required = false)UpmsUserinfoTQuery upmsUserinfoTQuery){
        //创建page对象
        Page<UpmsUserinfoT> userinfoTPage = new Page<UpmsUserinfoT>(current,limit);
        //构建条件
        QueryWrapper<UpmsUserinfoT> wrapper = new QueryWrapper<UpmsUserinfoT>();
        //多条件组合查询
        String userinfoAlias = upmsUserinfoTQuery.getUserinfoAlias();
        String userinfoName=upmsUserinfoTQuery.getUserinfoName();
        String userinfoStatus=upmsUserinfoTQuery.getUserinfoStatus();
        Date creationDate=upmsUserinfoTQuery.getCreationDate();
        Date lastUpdatedDate=upmsUserinfoTQuery.getLastUpdatedDate();
        //判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(userinfoAlias)){
            wrapper.like("userinfo_alias",userinfoAlias);
        }
        if (!StringUtils.isEmpty(userinfoName)){
            wrapper.eq("userinfo_name",userinfoName);
        }
        if (!StringUtils.isEmpty(userinfoStatus)){
            wrapper.eq("userinfo_status",userinfoStatus);
        }
        if (!StringUtils.isEmpty(creationDate)){
            wrapper.ge("creation_date",creationDate);
        }
        if (!StringUtils.isEmpty(lastUpdatedDate)){
            wrapper.le("last_updated_date",lastUpdatedDate);
        }

        //调用方法实现条件查询分页
        upmsUserinfoTService.page(userinfoTPage,wrapper);
        //总记录数
        long total = userinfoTPage.getTotal();

        List<UpmsUserinfoT> records = userinfoTPage.getRecords();
        return ResultData.success(records);
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

