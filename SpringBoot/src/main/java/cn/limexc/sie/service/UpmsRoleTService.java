package cn.limexc.sie.service;

import cn.limexc.sie.entity.UpmsRoleT;
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
public interface UpmsRoleTService extends IService<UpmsRoleT> {

    //根据用户id获取用户角色列表
    List<UpmsRoleT> selectRoleByUserId(int uid);
}
