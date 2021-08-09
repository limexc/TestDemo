package cn.limexc.sie.service.impl;

import cn.limexc.sie.service.UpmsMenuTService;
import cn.limexc.sie.service.UpmsRofuTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 暂时弃用自定义
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/9 10:38
 */
interface TestPermissionEvaluator {

    boolean check(Authentication authentication);
}

@Slf4j
@Service("testPermissionEvaluator")
public class TestPermissionEvaluatorImpl implements TestPermissionEvaluator {
    @Autowired
    UpmsMenuTService menuTService;

    public boolean check(Authentication authentication) {
        String userAlias = SecurityContextHolder.getContext().getAuthentication().getName();
        //通过userAlias获得menu列表
        List<String> menuValues = menuTService.selectMenuValueByUserAlias(userAlias);
        boolean letsGo = false;
        for (String str:menuValues) {

        }
        //UsernamePasswordAuthenticationToken
        // [
        // Principal=xianzhiyuan,
        // Credentials=[PROTECTED],
        // Authenticated=true,
        // Details=null,
        // Granted
        // Authorities=[系统管理, 查询用户]
        // ]
        System.out.println("进入了自定义的匹配器" + authentication);
        return letsGo;
    }

}
