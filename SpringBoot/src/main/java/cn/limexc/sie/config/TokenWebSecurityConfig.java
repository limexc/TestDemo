package cn.limexc.sie.config;


import cn.limexc.sie.filter.TokenAuthenticationFilter;
import cn.limexc.sie.filter.TokenLoginFilter;
import cn.limexc.sie.security.DefaultPasswordEncoder;
import cn.limexc.sie.security.TokenLogoutHandler;
import cn.limexc.sie.security.TokenManager;
import cn.limexc.sie.security.UnauthorizedEntryPoint;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsUtils;


/**
 * <p>
 * Security核心配置类
 * </p>
 *
 * @author qy
 * @since 2019-11-18
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)// 控制权限注解
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    //数据库返回 自定义
    private UserDetailsService userDetailsService;
    //token管理
    private TokenManager tokenManager;
    //密码处理
    private DefaultPasswordEncoder defaultPasswordEncoder;
    //redis
    private RedisTemplate redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().authorizeRequests()
                .anyRequest()
                .authenticated()
                // 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
                .and().cors()
                .and().csrf().disable()
                //登录表单提交地址
                .formLogin().loginProcessingUrl("/sys/api/user/login")
                //请求退出的地址
                .and().logout().logoutUrl("/sys/api/user/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
                //添加拦截器
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();

        ;

        // 禁用缓存
        http.headers().cacheControl();
        //允许跨域
        http.headers().frameOptions().disable();

    }



    /**
     * 密码处理
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置哪些请求不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //无条件允许访问"/sys/api/user/login",
        web.ignoring().antMatchers(
                "/csrf/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/v2/**",
                "/swagger-ui.html/**"
               );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}