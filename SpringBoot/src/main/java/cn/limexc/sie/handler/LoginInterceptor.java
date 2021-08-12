package cn.limexc.sie.handler;

import cn.limexc.sie.security.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 弃用,删掉就可以.不会影响任何功能.
 * @Version 0.0.1
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/21 17:35
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private TokenManager tokenManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截所有根请求判断是否登录
        //获取请求的URI
        //准备使用springsecurity框架解决
        String uri = request.getRequestURI();
        //HttpSession session = request.getSession();
        //UpmsUserT sysUser = (UpmsUserT) session.getAttribute("sysUser");

        log.info("访问的URI是：{}",uri);

        return true;

        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
