package cn.limexc.sie.service;

import cn.limexc.sie.entity.UpmsMenuT;
import cn.limexc.sie.entity.subject.IndexSub;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 * UPMS权限管理 菜单表 服务类
 * </p>
 *
 * @author 贤致源
 * @since 2021-07-27
 */
public interface UpmsMenuTService extends IService<UpmsMenuT> {

    //boolean addMenu(UpmsMenuT upmsMenuT);

    //List<IndexSub> getMenuTree();

    List<UpmsMenuT> queryAllMenu();

    List<String> selectMenuValueByUserId(String id);
    List<String> selectMenuValueByUserAlias(String userAlias);

    List<JSONObject> selectMenuByUserId(Integer userId);
}
