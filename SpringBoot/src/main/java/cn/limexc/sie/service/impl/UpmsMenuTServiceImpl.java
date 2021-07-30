package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.entity.subject.FunSub;
import cn.limexc.sie.entity.subject.IndexSub;
import cn.limexc.sie.entity.subject.MenuSub;
import cn.limexc.sie.mapper.UpmsMenuTMapper;


import cn.limexc.sie.service.UpmsMenuTService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * UPMS权限管理 菜单表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Service
public class UpmsMenuTServiceImpl extends ServiceImpl<UpmsMenuTMapper, UpmsMenuT> implements UpmsMenuTService {
    /**
     *
     */
    @Autowired
    private UpmsMenuTMapper menuTMapper;

    @Override
    public List<IndexSub> getMenuTree() {
        //查询所有1级分类  menuType==目录
        QueryWrapper<UpmsMenuT> wrapperIndex = new QueryWrapper<>();
        wrapperIndex.eq("menu_type","目录");
        List<UpmsMenuT> indexList = baseMapper.selectList(wrapperIndex);

        //查询所有2级分类  menuType==菜单
        QueryWrapper<UpmsMenuT> wrapperMenu = new QueryWrapper<>();
        wrapperMenu.eq("menu_type","菜单");
        List<UpmsMenuT> menuList = baseMapper.selectList(wrapperMenu);

        //查询所有3级分类 menuType==功能
        QueryWrapper<UpmsMenuT> wrapperFun = new QueryWrapper<>();
        wrapperFun.eq("menu_type","功能");
        List<UpmsMenuT> funList = baseMapper.selectList(wrapperFun);

        //创建集合用于封装最后数据
        List<IndexSub> finalIndexList = new ArrayList<>();

        //封装1级分类
        for (UpmsMenuT index:indexList) {
            IndexSub indexSub = new IndexSub();
            //将index的值复制到indexSub中
            BeanUtils.copyProperties(index,indexSub);
            finalIndexList.add(indexSub);
            //准备封装2级分类
            List<MenuSub> finalMenuList = new ArrayList<>();
            //遍历2级分类集合
            for (UpmsMenuT menu:menuList) {
                MenuSub menuSub = new MenuSub();
                //判断二级分类upper与一级分类id是否一致
                if (menu.getMenuUpper().equals(index.getMenuId())){
                    BeanUtils.copyProperties(menu,menuSub);
                    finalMenuList.add(menuSub);
                }
                //准备封装3级分类
                List<FunSub> finalFunSub = new ArrayList<>();
                //遍历3级分类
                for (UpmsMenuT fun:funList) {
                    if (fun.getMenuUpper().equals(menu.getMenuId())){
                        FunSub funSub = new FunSub();
                        BeanUtils.copyProperties(fun,funSub);
                        finalFunSub.add(funSub);
                    }
                }
                menuSub.setChildren2(finalFunSub);

            }
            indexSub.setChildren(finalMenuList);
        }

        return finalIndexList;
    }

    /**
     @Override
     public boolean addMenu(UpmsMenuT upmsMenuT) {

     menuTMapper.insert(upmsMenuT);

     return false;
     }
     */


}
