package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsRofuTQuery;
import cn.limexc.sie.mapper.UpmsRofuTMapper;
import cn.limexc.sie.mapper.UpmsRoleTMapper;
import cn.limexc.sie.mapper.UpmsUserTMapper;
import cn.limexc.sie.service.UpmsRofuTService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * UPMS权限管理 用户角色表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-27
 */
@Service
public class UpmsRofuTServiceImpl extends ServiceImpl<UpmsRofuTMapper, UpmsRofuT> implements UpmsRofuTService {

    @Autowired
    UpmsRofuTMapper rofuTMapper;
    @Autowired
    UpmsRoleTMapper roleTMapper;
    @Autowired
    UpmsUserTMapper userTMapper;

    @Override
    public boolean addRofu(UpmsRofuT upmsRofuT) {
        //分别使用uid和rid查询user表与role表
        UpmsRoleT roleT = roleTMapper.selectById(upmsRofuT.getRofuRoleid());
        UpmsUserT userT = userTMapper.selectById(upmsRofuT.getRofuUserid());
        if (roleT!=null&&userT!=null){
            int row = rofuTMapper.insert(upmsRofuT);
            if (row>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UpmsRofuTQuery> listRofu(UpmsUserT userT) {
        //先查全部的角色
        // QueryWrapper<UpmsMenuT> wrapperIndex = new QueryWrapper<>();
        // wrapperIndex.eq("menu_type","目录");
        // List<UpmsMenuT> indexList = baseMapper.selectList(wrapperIndex);
        QueryWrapper<UpmsRoleT> wapperRole = new QueryWrapper<>();
        List<UpmsRoleT> allRole = roleTMapper.selectList(wapperRole);

        QueryWrapper<UpmsRofuT> wapperRofu = new QueryWrapper<>();
        wapperRofu.eq("rofu_userid",userT.getUserId());
        List<UpmsRofuT> rofuTList =rofuTMapper.selectList(wapperRofu);
        //---------------------暂时到这里，想法两个list进行匹配------------------------\\
        List<UpmsRofuTQuery> rofuTVos = new ArrayList<>();

        return null;
    }

    @Override
    public List<UpmsRoleT> listRole(int uid) {
        QueryWrapper<UpmsRofuT> wapperRofu = new QueryWrapper<>();
        wapperRofu.eq("rofu_userid",uid);
        List<UpmsRofuT> rofuTList =rofuTMapper.selectList(wapperRofu);
        //需要获取list中的roleid
        List<String> roleIdList = new ArrayList<>();
        //------------------未完成---------------\\

        QueryWrapper<UpmsRoleT> wapperRole = new QueryWrapper<>();
        //wapperRole.eq("role_id",);
        //List<UpmsRoleT> roleTList = roleTMapper.selectList();

        return null;
    }

}
