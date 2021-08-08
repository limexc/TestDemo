package cn.limexc.sie.filter;


import cn.limexc.sie.security.TokenManager;
import cn.limexc.sie.util.ResponseCode;
import cn.limexc.sie.util.ResponseUtil;
import cn.limexc.sie.util.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 访问过滤器 授权过滤
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager,RedisTemplate redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        log.info("=================>  "+req.getRequestURI());
        if(req.getRequestURI().indexOf("/sys") == -1) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;
        try {
            log.info("getAuthentication: "+req.getHeader("token"));
            authentication = getAuthentication(req);
        } catch (Exception e) {
            ResponseUtil.out(res, ResultData.fail(ResponseCode.ERROR.val(), "错误,token"));
        }

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            //ResponseUtil.out(res, ResultData.fail(ResponseCode.ERROR.val(), "错误,authentication为空!"));
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        log.info("在getAuthentication中,getHeader(`token`): "+request.getHeader("token"));
        if (token != null && !"".equals(token.trim())) {
            String userAlias = tokenManager.getUserFromToken(token);

            List<String> menuValueList = (List<String>) redisTemplate.opsForValue().get(userAlias);
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            for(String menuValue: menuValueList) {
                if(StringUtils.isEmpty(menuValue)){continue;}
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(menuValue);
                authorities.add(authority);
            }

            if (!StringUtils.isEmpty(userAlias)) {
                return new UsernamePasswordAuthenticationToken(userAlias, token, authorities);
            }
            return null;
        }
        return null;
    }
}