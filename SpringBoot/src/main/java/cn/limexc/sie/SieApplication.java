package cn.limexc.sie;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ADMIN
 */

@SpringBootApplication
@ComponentScan(basePackages = {"cn.limexc"})
@EnableTransactionManagement
public class SieApplication {

    protected final static Logger logger = LoggerFactory.getLogger(SieApplication.class);


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SieApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("PortalApplication is success!");
        System.err.println("sample started. http://localhost:8081");


        //SpringApplication.run(SieApplication.class, args);
    }

}
