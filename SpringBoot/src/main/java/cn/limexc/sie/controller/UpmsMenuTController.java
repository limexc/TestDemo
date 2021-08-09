package cn.limexc.sie.controller;


import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.entity.UpmsRoahT;
import cn.limexc.sie.entity.vo.UpmsMenuTQuery;
import cn.limexc.sie.service.UpmsMenuTService;
import cn.limexc.sie.service.UpmsRoahTService;
import cn.limexc.sie.util.RemoveTreeNodeUtil;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResultData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/sys/menu")
public class UpmsMenuTController {

    @Autowired
    private UpmsMenuTService upmsMenuTService;
    @Autowired
    private UpmsRoahTService upmsRoahTService;

    // 增
    @PreAuthorize("hasAuthority('新增菜单')")
    @PostMapping("/addmenu")
    public ResultData addMenu(@RequestBody UpmsMenuT upmsMenuT){
        //拿到数据对内容进行格式校验

        boolean isSave = upmsMenuTService.save(upmsMenuT);
        if (isSave){
            return ResultData.success(isSave);
        }

        return ResultData.fail(ResponseCode.ERROR.val(), "");
    }


    /**
     * 删  需要判断该菜单下是否还存在数据
     * 此方法需要接受的不应该仅仅是一个id,有可能是一个id的列表,
     * 用于做批量删除,类似于批量的删除用户
     * 用一个循环来删除?感觉会很慢,而且一次循环后可能将子节点已经删除了,再次循环会出错误
     * @param ids  id列表
     * @return
     */
    @PreAuthorize("hasAuthority('删除菜单')")
    @DeleteMapping("/delmenu")
    public ResultData delMenus(@RequestParam(value = "ids") String ids){

        //获取表全部数据
        List<UpmsMenuT> menuTList = upmsMenuTService.list();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (UpmsMenuT menuT: menuTList) {
            if (menuT.getMenuUpper()==null){
                menuT.setMenuUpper(0);
            }
            map.put(menuT.getMenuId(),menuT.getMenuUpper());
        }
        List<String> renode = RemoveTreeNodeUtil.RemoveList(map,ids);
        boolean isDel = upmsMenuTService.removeByIds(renode);
        if (isDel){
            return ResultData.success(isDel);
        }

        return ResultData.fail(ResponseCode.ERROR.val(), "未能删除数据");
    }



    // 改
    @PreAuthorize("hasAuthority('修改菜单')")
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

    @PreAuthorize("hasAuthority('查询菜单')")
    @GetMapping("/uppermenu")
    public ResultData listUpperMenu(){
        //需要向前端返回所有二级以上菜单与目录的名称及id
        //格式{value: 'ID',label: '名称'}
        //获取系统内的 目录 和 菜单数据
        QueryWrapper<UpmsMenuT> queryWrapper = new QueryWrapper<UpmsMenuT>();
        queryWrapper.in("MENU_TYPE","目录","菜单");
        List<UpmsMenuT> allMenu = upmsMenuTService.list(queryWrapper);
        List<Map> finalMenuDate = new ArrayList<>();
        Map map1 = new HashMap();
        map1.put("value",0);
        map1.put("label","根目录");
        finalMenuDate.add(map1);
        for (UpmsMenuT menuT:allMenu) {
            Map map = new HashMap();
            map.put("value",menuT.getMenuId());
            map.put("label",menuT.getMenuName());
            finalMenuDate.add(map);
        }

        return ResultData.success(finalMenuDate);
    }




    // 查  全部与条件
    @PreAuthorize("hasAuthority('查询菜单')")
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
        log.info("菜单查询条件为:"+upmsMenuTQuery.toString());
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
    @PreAuthorize("hasAuthority('查询菜单')")
    @GetMapping("/getmenutree")
    public ResultData getTreeMenu(){
        List<UpmsMenuT> list = upmsMenuTService.queryAllMenu();

        return ResultData.success(list);
    }

    @PreAuthorize("hasAuthority('查询菜单')")
    @GetMapping("/menutreeofid")
    public ResultData getIdTreeMenu(@RequestParam("id") String id){
        List<UpmsMenuT> list = upmsMenuTService.queryAllMenu();
        //需要将与id匹配的menu传回,通过msg吧,开始设计的时候不太好,没时间改了
        //通过角色id获取
        QueryWrapper<UpmsRoahT> queryWrapper =new QueryWrapper<UpmsRoahT>();
        queryWrapper.eq("ROAH_ROLEID",id);
        List<UpmsRoahT> roahTS = upmsRoahTService.list(queryWrapper);
        List<Integer> finalSelect = new ArrayList<>();
        for (UpmsRoahT roahT:roahTS) {
            finalSelect.add(roahT.getRoahMenuid());
        }
        log.info(Arrays.toString(finalSelect.toArray()));
        return ResultData.success(list,Arrays.toString(finalSelect.toArray()));
    }

}

