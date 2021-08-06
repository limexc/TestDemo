package cn.limexc.sie.service;

import cn.limexc.sie.entity.vo.LoginInfoVo;
import cn.limexc.sie.entity.vo.LoginVo;

/**
 * @Description [类说明]
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/6 8:50
 */
public interface MemberService {

    String login(LoginVo loginVo);

    LoginInfoVo getLoginInfo(String userId);
}
