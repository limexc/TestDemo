package cn.limexc.sie.service;

import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.entity.vo.UpmsUserTQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;


/**
 * <p>
 * UPMS权限管理系统用户信息表 服务类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
public interface UpmsUserTService extends IService<UpmsUserT> {

    UpmsUserT selectByUserAlias(String userAlias);

    //void pageQuery(Page<UpmsUserT> pageParam, UpmsUserTQuery UpmsUserTQuery);

}
