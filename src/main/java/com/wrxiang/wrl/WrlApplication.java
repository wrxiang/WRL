package com.wrxiang.wrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description SpringBoot启动类
 * @Author wrxiang
 * @Date 2021/12/12 11:51
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages={"com"})
public class WrlApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(WrlApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run();
    }

}
