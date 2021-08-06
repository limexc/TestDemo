package cn.limexc.sie.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author 贤致源
 * @Create 2021/7/20 14:20
 */
@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper getPageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helperDialect","Oracle");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}

