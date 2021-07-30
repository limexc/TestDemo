package cn.limexc.sie.servicebase.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description [类说明]
 * @Version
 * @Email zhiyuanxzy@gmail.com
 * @Author ADMIN
 * @Create 2021/7/27 23:49
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .build();
    }


    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("用户权限管理系统API文档")
                .description("本文档描述了用户权限管理系统接口定义")
                .version("1.0")
                .contact(new Contact("limexc", "https://blog.limexc.cn", "zhiyuanxzy@gmail.com"))
                .build();
    }

}
