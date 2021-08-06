package cn.limexc.sie.controller;

import cn.limexc.sie.entity.vo.LoginInfoVo;
import cn.limexc.sie.entity.vo.LoginVo;
import cn.limexc.sie.exception.UserException;
import cn.limexc.sie.service.MemberService;
import cn.limexc.sie.util.JwtUtils;
import cn.limexc.sie.util.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 专门用于用户登录的等 相关的
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/8/3 14:03
 */
@RestController
@CrossOrigin
@RequestMapping("/public/user")
public class UserLoginController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginVo loginVo){
        Map map = new HashMap();
        String token= memberService.login(loginVo);
        map.put("token",token);


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

}
