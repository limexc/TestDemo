package cn.limexc.sie.service;

import cn.limexc.sie.entity.UpmsAuthT;
import cn.limexc.sie.entity.UpmsRoahT;
import cn.limexc.sie.entity.UpmsRoleT;
import cn.limexc.sie.entity.vo.UpmsRoahTVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * UPMS权限管理 角色信息表 服务类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
public interface UpmsRoahTService extends IService<UpmsRoahT> {

    boolean addRoleAuth(UpmsRoahTVO roahTVO);

    boolean editRoleAuth(UpmsRoahTVO roahTVO);

    List<UpmsAuthT> listRoleAuth(String rid);

}
