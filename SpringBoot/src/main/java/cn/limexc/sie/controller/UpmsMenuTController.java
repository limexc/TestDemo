package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.entity.vo.UpmsMenuTQuery;
import cn.limexc.sie.service.UpmsMenuTService;
import cn.limexc.sie.util.RemoveTreeNodeUtil;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * UPMS权限管理 菜单表 前端控制器
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/menu")
public class UpmsMenuTController {

    @Autowired
    private UpmsMenuTService upmsMenuTService;

    // 增
    @PostMapping("/addmenu")
    public ResultData addMenu(@RequestBody UpmsMenuT upmsMenuT){
        //拿到数据对内容进行格式校验

        boolean isSave = upmsMenuTService.save(upmsMenuT);
        if (isSave){
            return ResultData.success(isSave);
        }

        return ResultData.fail(ResponseCode.ERROR.val(), "");
    }


    // 删  需要判断该菜单下是否还存在数据
    @DeleteMapping("/delmenu")
    public ResultData delMenu(@RequestParam(value = "id") String id){
//        QueryWrapper wrapper = new QueryWrapper();
//        //通过id查找所有父目录为此id的数据  如果该目录的子目录下也有数据？
//
//        wrapper.eq("menu_upper",id);
//        List<UpmsMenuT> records = upmsMenuTService.listObjs(wrapper);
//        List<String> ids = new ArrayList<String>();
//        for (UpmsMenuT menu:records) {
//            ids.add(String.valueOf(menu.getMenuUpper()));
//        }
//        ids.add(id);
        //获取表全部数据
        List<UpmsMenuT> menuTList = upmsMenuTService.list();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (UpmsMenuT menuT: menuTList) {
            if (menuT.getMenuUpper()==null){
                menuT.setMenuUpper(0);
            }
            map.put(menuT.getMenuId(),menuT.getMenuUpper());
        }
        List<String> renode = RemoveTreeNodeUtil.RemoveList(map,id);
        boolean isDel = upmsMenuTService.removeByIds(renode);
        if (isDel){
            return ResultData.success(isDel);
        }

        return ResultData.fail(ResponseCode.ERROR.val(), "未能删除数据");
    }



    // 改
    @PostMapping("/editmenu")
    public ResultData editMenu(@RequestBody UpmsMenuT upmsMenuT){
        boolean change = false;
        try {
            //用来判断传入的数据是否符合要求  后面单独抽取
            change = upmsMenuTService.updateById(upmsMenuT);
        if (change) {
            return ResultData.success(change);
        }
            return ResultData.fail(ResponseCode.ERROR.val(), "用户信息修改失败");
        }catch(Exception e){
            e.printStackTrace();
            return ResultData.fail(ResponseCode.ERROR.val(), "数据库错误");
        }
    }






    // 查  全部与条件
    @PostMapping("/findmenu")
    public ResultData pageMenuCondition(
            @ApiParam(name = "current",value = "页码",required = true)
            @RequestParam(value = "current",defaultValue = "1") int current,
            @ApiParam(name = "limit",value = "分页大小",required = true)
            @RequestParam(value = "limit",defaultValue = "5") int limit,
            @ApiParam(name = "upmsUserTQuery",value = "查询条件",required = false)
            @RequestBody(required = false) UpmsMenuTQuery upmsMenuTQuery){
        //创建page对象
        Page<UpmsMenuT> menuTPage = new Page<UpmsMenuT>(current,limit);
        //构建条件
        QueryWrapper<UpmsMenuT> wrapper = new QueryWrapper<UpmsMenuT>();

        if (upmsMenuTQuery!=null){

        }
        //多条件组合查询
        String menuName = upmsMenuTQuery.getMenuName();
        String menuType = upmsMenuTQuery.getMenuType();
        String menuStatus = upmsMenuTQuery.getMenuStatus();
        int menuId = upmsMenuTQuery.getMenuId();

        //判断条件是否为空，如果不为空则拼接条件
        if (!StringUtils.isEmpty(menuName)){
            wrapper.like("menu_name",menuName);
        }
        if (!StringUtils.isEmpty(menuType)){
            wrapper.eq("menu_type",menuType);
        }
        if (!StringUtils.isEmpty(menuStatus)){
            wrapper.eq("menu_status",menuStatus);
        }
        if (menuId!=0){
            wrapper.eq("menu_id",menuId);
        }


        //调用方法实现条件查询分页
        upmsMenuTService.page(menuTPage,wrapper);
        //总记录数
        long total = menuTPage.getTotal();
        List<UpmsMenuT> records = menuTPage.getRecords();
        return ResultData.success(records,String.valueOf(total));
    }

/**
    @GetMapping("/getmenu")
    public ResultData getTreeMenu(){
        //list集合泛型为1级分类  目录 --  菜单
        List<IndexSub> list =upmsMenuTService.getMenuTree();

        return ResultData.success(list);
    }
*/
    @GetMapping("/getmenutree")
    public ResultData getTreeMenu(){
        List<UpmsMenuT> list = upmsMenuTService.queryAllMenu();

        return ResultData.success(list);
    }


}

