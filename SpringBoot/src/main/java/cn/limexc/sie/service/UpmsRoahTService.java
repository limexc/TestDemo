package cn.limexc.sie.service;

import cn.limexc.sie.entity.UpmsRoahT;
import cn.limexc.sie.entity.vo.UpmsRoahTVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * UPMS权限管理 角色信息表 服务类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
public interface UpmsRoahTService extends IService<UpmsRoahT> {

    boolean addRoleAuth(UpmsRoahTVo roahTVO);

    boolean editRoleAuth(UpmsRoahTVo roahTVO);

    //AuthListTree listRoleAuth(String rid);

}
