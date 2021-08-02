package cn.limexc.sie.service.impl;

import cn.limexc.sie.entity.UpmsAuthT;
import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.entity.UpmsRoahT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.vo.UpmsRoahTVO;
import cn.limexc.sie.mapper.UpmsAuthTMapper;
import cn.limexc.sie.mapper.UpmsRoahTMapper;
import cn.limexc.sie.mapper.UpmsRoleTMapper;
import cn.limexc.sie.service.UpmsRoahTService;
import cn.limexc.sie.service.UpmsRoleTService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UpmsRoahTServiceImpl extends ServiceImpl<UpmsRoahTMapper, UpmsRoahT> implements UpmsRoahTService {
    @Autowired
    private UpmsRoahTMapper roahTMapper;

    @Autowired
    private UpmsAuthTMapper authTMapper;

    @Override
    public boolean addRoleAuth(UpmsRoahTVO roahTVO) {
        int rows = roahTMapper.insertRoahList(roahTVO.getRoleId(), roahTVO.getAuthIds());
        if (rows>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean editRoleAuth(UpmsRoahTVO roahTVO) {
        //要包含从某个角色中删除权限，即前端用户取消勾选某一权限。
        //如果之前已经存在不修改，若不存在就插入，若List中不存在就删除
        //获得当前角色已经勾选的权限，对比list
        //在提交时判断是插入还是删除？应该可以和新增进行合并，使用该接口即可。
        int rows = 0;

        QueryWrapper<UpmsRoahT> wrapper = new QueryWrapper<>();
        wrapper.eq("ROAH_ROLEID",roahTVO.getRoleId());
        List<UpmsRoahT> roahList = baseMapper.selectList(wrapper);
        List<String> oldRoahList = new ArrayList();
        for (UpmsRoahT r : roahList) {
            oldRoahList.add(String.valueOf(r.getRoahRoleid()));
        }

        List<String> newRoahList = roahTVO.getAuthIds();
        //移除两个list中相同的值，从数据库中读取的即为要删除的，传入的是需要插入的。
        nextfor:
        for (int i=0;i< oldRoahList.size();i++){
            for (int j=0;j<newRoahList.size();j++){
                if (oldRoahList.get(i)==oldRoahList.get(j)){
                    oldRoahList.remove(i);
                    newRoahList.remove(j);
                    j++;
                    i++;
                    continue nextfor;
                }
            }
        }
        System.out.println("------要删除的数据------");
        for (String a:oldRoahList) {
            System.out.print(a+" ");
        }
        System.out.println();
        if (oldRoahList.size()!=0){
            rows = roahTMapper.updateRoahList(roahTVO.getRoleId(),oldRoahList);
        }

        System.out.println("------要增加的数据------");
        for (String b:newRoahList) {
            System.out.print(b+" ");
        }
        System.out.println();


        if (newRoahList.size()!=0){
            rows = roahTMapper.insertRoahList(roahTVO.getRoleId(),newRoahList);
        }
        if (rows!=0){
            return true;
        }
        return false;
    }

    @Override
    public List<UpmsAuthT> listRoleAuth(String rid) {
        QueryWrapper<UpmsRoahT> roahWrapper = new QueryWrapper<>();
        roahWrapper.eq("ROAH_ROLEID",rid);
        List<UpmsRoahT> roahTList = baseMapper.selectList(roahWrapper);

        QueryWrapper<UpmsAuthT> authWrapper = new QueryWrapper<>();
        List<UpmsAuthT> authTList = authTMapper.selectList(authWrapper);
        //===========看看前端需要什么样的数据===UpmsAuthTVo===========\\

        return null;
    }
}
