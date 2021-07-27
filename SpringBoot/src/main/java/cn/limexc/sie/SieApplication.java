package cn.limexc.sie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author ADMIN
 */

@SpringBootApplication
@ComponentScan(basePackages = {"cn.limexc"})
public class SieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SieApplication.class, args);
	}

}
