package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.mapper.UpmsRoleTMapper;
import cn.limexc.sie.service.UpmsRofuTService;
import cn.limexc.sie.service.UpmsRoleTService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * UPMS权限管理 角色信息表 服务实现类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Service
@Slf4j
public class UpmsRoleTServiceImpl extends ServiceImpl<UpmsRoleTMapper, UpmsRoleT> implements UpmsRoleTService {

    @Autowired
    UpmsRofuTService rofuTService;
    @Autowired
    UpmsRoleTMapper roleTMapper;

    @Override
    public List<UpmsRoleT> selectRoleByUserId(int uid) {
        //通过用户id查询 rofu表,列出全部与之绑定的roleid
        QueryWrapper<UpmsRofuT> rofuTQueryWrapper = new QueryWrapper<UpmsRofuT>();
        rofuTQueryWrapper.eq("ROFU_USERID",uid);
        List<UpmsRofuT> rofuTList = rofuTService.list(rofuTQueryWrapper);
        log.info("该用户具有:{}种角色.",rofuTList.size());
        //获得角色id进行对角色的查询
        List<String> roleIds = new ArrayList<String>();
        if (rofuTList.size()>0){
            for (UpmsRofuT rofu:rofuTList) {
                roleIds.add(String.valueOf(rofu.getRofuRoleid()));
            }
        }
        QueryWrapper<UpmsRoleT> roleQueryWrapper = new QueryWrapper<UpmsRoleT>();
        roleQueryWrapper.eq("ROLE_STATUS","Act");
        roleQueryWrapper.in("ROLE_ID",roleIds);
        //通过判读status字段来决定是否有效,是否将该角色放到集合中
        List<UpmsRoleT> roleTList = roleTMapper.selectList(roleQueryWrapper);
        //roleTMapper.selectBatchIds(roleIds);

        return roleTList;
    }
}
