package cn.limexc.sie.service;

import cn.limexc.sie.entity.UpmsRofuT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsRofuTQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * UPMS权限管理 用户角色表 服务类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
public interface UpmsRofuTService extends IService<UpmsRofuT> {

    boolean addRofu(UpmsRofuT upmsRofuT);

    List<UpmsRofuTQuery> listRofu(String uid);

    //UpmsRoleT getRoleById(int id);
}
