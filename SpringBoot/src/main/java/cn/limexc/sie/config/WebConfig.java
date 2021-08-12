package cn.limexc.sie.config;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/22 10:56
 */


import cn.limexc.sie.handler.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 * 可以弃用,暂时未删除
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 添加Web项目的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有访问路径，都通过LoginInterceptor类型的拦截器进行拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}

