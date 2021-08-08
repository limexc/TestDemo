package cn.limexc.sie.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * 根据用户名获取用户登录信息
     * @param userAlias
     * @return
     */
    Map<String, Object> getUserInfo(String userAlias);

    /**
     * 根据用户名获取动态菜单
     * @param userAlias
     * @return
     */
    List<JSONObject> getMenu(String userAlias);

}
