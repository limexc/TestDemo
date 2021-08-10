package cn.limexc.sie.filter;

import cn.limexc.sie.entity.SecurityUser;
import cn.limexc.sie.entity.UpmsUserT;
import cn.limexc.sie.security.TokenManager;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResponseUtil;
import cn.limexc.sie.util.ResultData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，
 * 对用户名密码进行登录校验
 * 认证过滤
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        //设置请求登录的地址
        log.info("设置请求登录的地址为:/sys/api/user/login");
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/sys/api/user/login","POST"));
    }

    /**
     * attemptAuthentication 得到用户名 密码 进行认证
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            UpmsUserT user = new ObjectMapper().readValue(req.getInputStream(), UpmsUserT.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserAlias(),
                    user.getUserPasswd(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUserAlias());
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUserAlias(), user.getMenuValueList());
        Map map = new HashMap<>();
        map.put("token",token);
        log.info("token: "+token);
        ResponseUtil.out(res, ResultData.success(map));
        //ResponseUtil.out(res, R.ok().data("token", token));
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        //ResponseUtil.out(response, R.error());
        log.warn("错误,登录失败!");
        ResponseUtil.out(response, ResultData.fail(ResponseCode.ERROR.val(), "错误,登录失败!"));
    }
}
