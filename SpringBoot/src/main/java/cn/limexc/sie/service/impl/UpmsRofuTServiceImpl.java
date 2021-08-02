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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * UPMS权限管理 用户角色表 服务实现类
 * </p>
 *
 * @author 贤致源
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


    //@Transactional

    /**
     * 用于添加用户角色之间的绑定关系
     * @param upmsRofuT  用户角色对象
     * @return
     */
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


    /**
     * 列出用户角色绑定列表，已经选定的使用isSelect==true进行标识
     * @param uid  用户id
     * @return     角色绑定列表
     */
    @Override
    public List<UpmsRofuTQuery> listRofu(String uid) {
        //QueryWrapper<UpmsRofuT> wapperRofu = new QueryWrapper<>();
        //wapperRofu.eq("rofu_userid",uid);
        //List<UpmsRofuT> rofuTList =rofuTMapper.selectList(wapperRofu);
        //需要获取list中的roleid
        //List<String> roleIdList = new ArrayList<>();
        //QueryWrapper<UpmsRoleT> wapperRole = new QueryWrapper<>();
        //wapperRole.eq("role_id",);
        //List<UpmsRoleT> roleTList = roleTMapper.selectList();
        List<UpmsRofuTQuery> upmsRofuTQueries =rofuTMapper.selectAllRlById(uid);
        List<UpmsRofuTQuery> finalList = new ArrayList<>();
        for (UpmsRofuTQuery rofuTQuery:upmsRofuTQueries) {
            if (rofuTQuery.getRofuUserid()!=null){
                rofuTQuery.setSelect(true);
            }else {
                rofuTQuery.setSelect(false);
            }
            finalList.add(rofuTQuery);
            //System.out.println(rofuTQuery.toString());
        }

        return finalList;
    }

}
