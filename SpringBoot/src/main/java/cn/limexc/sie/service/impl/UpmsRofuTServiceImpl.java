package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsRoahT;
import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsRofuTQuery;
import cn.limexc.sie.entity.vo.UpmsRofuVo;
import cn.limexc.sie.mapper.UpmsRofuTMapper;
import cn.limexc.sie.mapper.UpmsRoleTMapper;
import cn.limexc.sie.mapper.UpmsUserTMapper;
import cn.limexc.sie.service.UpmsRofuTService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * UPMS权限管理 用户角色表 服务实现类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
@Slf4j
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
        //查询全部role,并将与该用户id绑定的role做标记 select=true
        List<UpmsRofuTQuery> upmsRofuTQueries =rofuTMapper.selectAllRlById(uid);
        //创建返回list
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

    @Override
    @Transactional
    public boolean updataByUidAndRoleList(UpmsRofuVo userRoleVo) {
        int rows = 0;
        QueryWrapper<UpmsRofuT> wrapper = new QueryWrapper<>();
        //通过userid获取表内未删除的绑定对象
        wrapper.eq("ROFU_USERID",userRoleVo.getUserId());
        List<UpmsRofuT> rofuTList = baseMapper.selectList(wrapper);

        //获得表内绑定角色
        List<String> oldRofuList = new ArrayList();
        for (UpmsRofuT r : rofuTList) {
            oldRofuList.add(String.valueOf(r.getRofuRoleid()));
        }

        List<String> newRofuList = userRoleVo.getRoleIds();
        //移除两个list中相同的值，从数据库中读取的即为要删除的，传入的是需要插入的。
        nextfor:
        for (int i=0;i< oldRofuList.size();i++){
            for (int j=0;j<newRofuList.size();j++){
                if (newRofuList.get(j).equals(oldRofuList.get(i) )){
                    oldRofuList.remove(i);
                    newRofuList.remove(j);
                    j++;
                    i++;
                    continue nextfor;
                }
            }
        }
        log.warn("删除的数据==>{}", Arrays.toString(oldRofuList.toArray()));

        QueryWrapper<UpmsRofuT> delUserRole = new QueryWrapper<UpmsRofuT>();
        delUserRole.eq("ROFU_USERID",userRoleVo.getUserId());
        delUserRole.in("ROFU_ROLEID",oldRofuList);

        if (oldRofuList.size()!=0){
            rows = rofuTMapper.delete(delUserRole);
        }

        log.info("要增加的数据==>{}", Arrays.toString(newRofuList.toArray()));

        if (newRofuList.size()!=0){
            UpmsRofuT rofuT = new UpmsRofuT();
            for (int i=0;i<newRofuList.size();i++){
                //需要传入rofu实体类对象
                rofuT.setRofuUserid(Integer.parseInt(userRoleVo.getUserId()));
                rofuT.setRofuRoleid(Integer.parseInt(newRofuList.get(i)));
                rows += rofuTMapper.insert(rofuT);
            }
        }
        if (rows!=0){
            return true;
        }
        return false;
    }

}
