package cn.limexc.sie.controller;

import cn.limexc.sie.entity.vo.LoginInfoVo;
import cn.limexc.sie.entity.vo.LoginVo;
import cn.limexc.sie.exception.UserException;
import cn.limexc.sie.service.IndexService;
import cn.limexc.sie.service.MemberService;
import cn.limexc.sie.util.JwtUtils;
import cn.limexc.sie.util.ResultData;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 专门用于用户登录的等 相关的
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/3 14:03
 */
@RestController
@Slf4j
@RequestMapping("/sys/api")
public class UserLoginController {

    //@Autowired
    //private MemberService memberService;


    /**
     *
     @ApiOperation(value = "用户登录")
     @PostMapping("/login")
    public ResultData login(@RequestBody LoginVo loginVo){
        Map map = new HashMap();
        String token= memberService.login(loginVo);
        map.put("token",token);
        System.out.println("走的controller获得的token");

        return ResultData.success(map);
    }
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/info")
    public ResultData getLoginInfo(HttpServletRequest request){
        Map map = new HashMap();
        try {
            String userId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(userId);
            map.put("item",loginInfoVo);
            return ResultData.success(map);
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("error");
        }
    }

    @GetMapping("/logout")
    public ResultData logout(){

        return ResultData.success(true);
    }
*/

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    //
    @GetMapping("/info")
    public ResultData info(){
        //获取当前登录用户用户名
        String userAlias = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("用户{}通过info获得信息.",userAlias);
        Map<String, Object> userInfo = indexService.getUserInfo(userAlias);
        return ResultData.success(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("/menu")
    public ResultData getMenu(){
        //获取当前登录用户用户名
        String userAlias = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> menuList = indexService.getMenu(userAlias);
        Map map =new HashMap();
        map.put("menuList", menuList);
        return ResultData.success(map);
    }

    @PostMapping("/logout")
    public ResultData logout(){
        return ResultData.success(true);
    }
}
